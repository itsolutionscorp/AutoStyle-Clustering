module Roman

  ROMAN_NUMERALS = {
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

  def numerals
    Hash[ROMAN_NUMERALS.to_a.reverse]
  end

  def to_roman
    num = self
    numerals.each_with_object('') do |(dec, rom), text|
      text << rom && num -= dec while num / dec > 0
    end
  end

end

class Fixnum
  include Roman
end
