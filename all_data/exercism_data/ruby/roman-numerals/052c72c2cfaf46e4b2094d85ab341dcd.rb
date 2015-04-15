class Fixnum
  def to_roman
  	result = ""
    number = self
  	dictionary = Fixnum.roman_dictionary
    dictionary.keys.each do |r_numeral|
      while number >= r_numeral
        number -= r_numeral
        result << dictionary[r_numeral]
      end
    end
    result
  end 

  private

  def self.roman_dictionary
  	  { 1000 => "M",
  	    900 => "CM",
  	    500 => "D",
  	    400 => "CD",
  	    100 => "C",
  	    90 => "XC",
  	    50 => "L",
  	    40 => "XL",
  	    10 => "X",
  	    9 => "IX",
  	    5 => "V",
  	    4 => "IV",
  	    1 => "I"}
  end
end
