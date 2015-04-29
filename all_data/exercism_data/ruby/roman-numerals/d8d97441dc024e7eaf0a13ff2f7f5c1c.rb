# We open up the Fixnum class to add a way to convert to roman numerals
class Fixnum

  NUMERALS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M'
  }

  def to_roman
    number = self
    check_array = NUMERALS.keys.reverse
    roman_numeral = ''
    check_array.each_with_index do |value,idx|
      numerals_to_add =  number / value
      numerals_to_add.times { roman_numeral << NUMERALS[value] }
      number -= numerals_to_add * value
    end
    handle_4_and_9(roman_numeral)
    roman_numeral
  end

  def handle_4_and_9(roman_numeral)
    roman_numeral.gsub!('IIII','IV')
    roman_numeral.gsub!('XXXX','XL')
    roman_numeral.gsub!('CCCC','CD')

    roman_numeral.gsub!('VIV','IX')
    roman_numeral.gsub!('LXL','XC')
    roman_numeral.gsub!('DCD','CM')
  end
end
