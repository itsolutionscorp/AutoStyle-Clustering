class Fixnum

  ONES = {
    1 => 'I',
    2 => 'II',
    3 => 'III',
    4 => 'IV',
    5 => 'V',
    6 => 'VI',
    7 => 'VII',
    8 => 'VIII',
    9 => 'IX'
  }

  TENS = {
    1 => 'X',
    2 => 'XX',
    3 => 'XXX',
    4 => 'XL',
    5 => 'L',
    6 => 'LX',
    7 => 'LXX',
    8 => 'LXXX',
    9 => 'XC'
  }

  HUNDREDS = {
    1 => 'C',
    2 => 'CC',
    3 => 'CCC',
    4 => 'CD',
    5 => 'D',
    6 => 'DC',
    7 => 'DCC',
    8 => 'DCCC',
    9 => 'CM'
  }

  THOUSANDS = {
    1 => 'M',
    2 => 'MM',
    3 => 'MMM'
  }

  PLACE_HASH = {
    one: nil,
    ten: nil,
    hundred: nil,
    thousand: nil
  }
  
  def to_roman
    number_array = self.to_s.chars
    PLACE_HASH.each{|x, y| PLACE_HASH[x] = number_array.pop}
    roman_numeral_array = []

    if PLACE_HASH[:thousand] != nil
      roman_numeral_array << THOUSANDS[PLACE_HASH[:thousand].to_i]
    end
    if PLACE_HASH[:hundred] != nil
      roman_numeral_array << HUNDREDS[PLACE_HASH[:hundred].to_i]
    end
    if PLACE_HASH[:ten] != nil
      roman_numeral_array << TENS[PLACE_HASH[:ten].to_i]
    end
    if PLACE_HASH[:one] != nil
      roman_numeral_array << ONES[PLACE_HASH[:one].to_i]
    end
    roman_numeral_array.join
  end

end
