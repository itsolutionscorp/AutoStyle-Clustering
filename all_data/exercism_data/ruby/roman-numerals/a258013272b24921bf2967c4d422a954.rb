require 'pry-byebug'
ONES_HASH = {'1' => 'I', '2' => 'II', '3' => 'III', '4' => 'IV', '5' => 'V', '6' => 'VI', '7' => 'VII', '8' => 'VIII', '9' => 'IX', '0' => ''}
TENS_HASH = {'1' => 'X', '2' => 'XX', '3' => 'XXX', '4' => 'XL', '5' => 'L', '6' => 'LX', '7' => 'LXX', '8' => 'LXXX', '9' => 'XC', '0' => ''}
HUNDREDS_HASH = {'1' => 'C', '2' => 'CC', '3' => 'CCC', '4' => 'CD', '5' => 'D', '6' => 'DC', '7' => 'DCC', '8' => 'DCCC', '9' => 'CM', '0' => ''}
THOUSANDS_HASH = {'1' => 'M', '2' => 'MM', '3' => 'MMM'}

class Fixnum
	def to_roman
		return_string = ''
		self.to_s.split('').reverse.each_with_index do |num, ind|
			return_string = ONES_HASH[num] + return_string if ind == 0
			return_string = TENS_HASH[num] + return_string if ind == 1
			return_string = HUNDREDS_HASH[num] + return_string if ind == 2
			return_string = THOUSANDS_HASH[num] + return_string if ind == 3
		end
		return_string
	end
end
