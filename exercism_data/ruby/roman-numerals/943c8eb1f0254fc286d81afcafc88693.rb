class Fixnum

def to_roman
	final = ""
	map1 = {"0" => "", "1"=> "I", "2"=> "II", "3"=> "III", "4"=> "IV", "5"=> "V", "6"=> "VI", "7"=> "VII", "8"=> "VIII", "9"=> "IX"}
	map2 = {"0" => "", "1"=> "X", "2"=> "XX", "3"=> "XXX", "4"=> "XL", "5"=> "L", "6"=> "LX", "7"=> "LXX", "8"=> "LXXX", "9"=> "XC"}
	map3 = {"0" => "", "1"=> "C", "2"=> "CC", "3"=> "CCC", "4"=> "CD", "5"=> "D", "6"=> "DC", "7"=> "DCC", "8"=> "DCCC", "9"=> "CM"}
	map4 = {"1"=> "M", "2"=> "MM", "3"=> "MMM"}
	
	final << map4[self.inspect.chars.reverse[3]] if self >= 1000
	final << map3[self.inspect.chars.reverse[2]] if self >= 100
	final << map2[self.inspect.chars.reverse[1]] if self >= 10
	final << map1[self.inspect.chars.reverse[0]]

end

end
