use LWP::Simple;
use utf8;
use Encode;
binmode(STDOUT, ":encoding(utf8)");

print "台南國賓影城\n";
&function("http://www.atmovies.com.tw/showtime/theater_t06608_a06.html");
print "台南新光影城\n";
&function("http://www.atmovies.com.tw/showtime/theater_t06607_a06.html");
print "台南威秀影城\n";
&function("http://www.atmovies.com.tw/showtime/theater_t06609_a06.html");

sub function{
	$url = shift @_;
	$html = get($url);
	# print $html;
	@array = split('<|>',$html);
	$i = 1;
	
	while($i <= $#array){
		if($array[$i] =~ m|a href="/movie/\w+/"|){
			print "$array[$i+1]\n";
		}
	
		if($array[$i] =~ /(\d\d：\d\d)+/){
			print "$1\n";
		}
		$i++;
	}
	print "\n";
}
