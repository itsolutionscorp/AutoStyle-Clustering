class Fixnum
  THRESHOLD = {
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
    1    => 'I'
  }

  def to_roman
    return '' unless self > 0
    match = THRESHOLD.detect{ |num, roman| self >= num }
    match[1] + (self - match[0]).to_roman
  end
end
