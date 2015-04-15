class Fixnum
  def to_roman
    RomanizeNumber.to_roman self
  end
end

class RomanizeNumber

  ROMA = {
    1 => 'I',
    4 => 'IV',
    5 => 'V',
    9 => 'IX',
    10 => 'X',
    40 => 'XL',
    50 => 'L',
    90 => 'XC',
    100 => 'C',
    400 => 'CD',
    500 => 'D',
    900 => 'CM',
    1000 => 'M'
  }

  def self.to_roman(num)
    str = ""
    roman_hash(num).each { |number, count| str += ROMA[number] * count }
    str
  end

  def self.roman_hash(num)
    # convert number to hash of values according to ROMA dictionary
    hash = Hash.new(0)
    ROMA.keys.reverse.each do |number|
      nom = num / number
      if nom > 0
        hash[number] +=nom 
        num -= number * nom
      end
    end
    hash
  end

end
