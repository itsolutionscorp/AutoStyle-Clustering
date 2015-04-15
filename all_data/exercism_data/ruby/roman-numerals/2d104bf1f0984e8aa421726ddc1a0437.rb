require 'pry'

class Fixnum
  NUMERALS = {  1 => 'I',   5 => 'V',   10 => 'X', 50 => 'L',
                100 => 'C', 500 => 'D', 1000 => 'M' }

  PATTERN = { '0' => '',  '1' => '1',  '2' => '11',  '3' => '111',  '4' => '15',
              '5' => '5', '6' => '51', '7' => '511', '8' => '5111', '9' => '10' }

  THOUSANDS = { '1' => NUMERALS[1000] }
  HUNDREDS =  { '1' => NUMERALS[100], '5' => NUMERALS[500], '0' => NUMERALS[1000] }
  TENS =      { '1' => NUMERALS[10],  '5' => NUMERALS[50],  '0' => NUMERALS[100] }
  ONES =      { '1' => NUMERALS[1],   '5' => NUMERALS[5],   '0' => NUMERALS[10] }

  def to_roman
    # array = self.to_s.chars.map { |char| char.to_i }
    number_array = self.to_s.split('')
    number_of_digits = number_array.length
    roman_string = ''

    if number_of_digits >= 4
      digit = number_array.shift
      roman_string << PATTERN[digit].split('').map { |numeral| THOUSANDS[numeral] }.join
    end
    
    if number_of_digits >= 3
      digit = number_array.shift
      roman_string << PATTERN[digit].split('').map { |numeral| HUNDREDS[numeral] }.join
    end

    if number_of_digits >= 2
      digit = number_array.shift
      roman_string << PATTERN[digit].split('').map { |numeral| TENS[numeral] }.join
    end

    if number_of_digits >= 1
      digit = number_array.shift
      roman_string << PATTERN[digit].split('').map { |numeral| ONES[numeral] }.join
    end

    roman_string
  end
end
