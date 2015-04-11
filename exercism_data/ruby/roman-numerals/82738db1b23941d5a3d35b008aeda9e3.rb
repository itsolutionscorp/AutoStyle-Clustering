class Fixnum
  def to_roman
    self <= 0 ? "" : match_numerals
  end

  private

  def match_numerals
    build_roman_string(*ROMAN.detect {|x, _| x <= self })
  end

  def build_roman_string(x, str)
    str + (self - x).to_roman
  end

  ROMAN = {
    1000 => 'M',
    900  => 'CM',
    500  => 'D',
    400  => 'CD',
    100  => 'C',
    90   => 'XC',
    50   => 'L',
    40   => 'XL',
    10   => 'X',
    9    => 'IX',
    5    => 'V',
    4    => 'IV',
    1    => 'I',
  }
end
