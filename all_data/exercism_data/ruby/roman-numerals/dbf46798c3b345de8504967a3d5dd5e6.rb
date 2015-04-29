class Fixnum
  def to_roman
    number = self
    roman=''
    roman_numerals_dictionary.each do |roman_number, roman_letter|
      fits = (number / roman_number).floor
      fits.times{ roman << roman_letter }
      number -= fits*roman_number
    end
    roman
  end

  private

  def roman_numerals_dictionary
    {
      1000 => "M",
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
      1 => "I",
    }
  end
end
