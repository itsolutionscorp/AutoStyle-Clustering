class Fixnum
	ROMAN_NUMERALS = { 
		1000 	=> "M", 
		900 	=> "CM", 
		500 	=> "D", 
		400 	=> "CD", 
		100		=> "C", 
		90 		=> "XC",  
		50 		=> "L", 
		40 		=> "XL", 
		10 		=> "X", 
		9 		=> "IX", 
		5			=> "V", 
		4			=> "IV", 
		1			=> "I" }
	ROMAN = 1
	ARABIC = 0
	def to_roman
		"#{first_piece[ROMAN]}#{(self - first_piece[ARABIC]).to_roman}" if self > 0
	end

private
	def first_piece
		ROMAN_NUMERALS.select { |key, value| key <= self}.first
	end
end
