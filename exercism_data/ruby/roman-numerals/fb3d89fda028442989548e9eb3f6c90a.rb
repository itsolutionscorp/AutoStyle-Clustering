module RomanNumeral
  def self.numeral_hash
    {1000 => 'M',
      900 => 'CM',
      500 => 'D',
      400 => 'CD',
      100 => 'C',
      90 => 'XC',
      50 => 'L',
      40 => 'XL',
      10 => 'X',
      9 => 'IX',
      5 => 'V',
      4 => 'IV',
      1 => 'I'
    }
  end
end

class Fixnum
  include RomanNumeral

  def to_roman
    numeral_string = ''
    self_dup = self
    until self_dup == 0
      RomanNumeral.numeral_hash.each do |key, value|
        if key <= self_dup
          self_dup -= key
          numeral_string += value
          break
        end
      end
    end
    numeral_string
  end

end
