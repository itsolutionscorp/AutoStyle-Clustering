class Fixnum

  CONVERT = {
    1000 => 'M',
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

  def to_roman
    i = self
    s = ''

    CONVERT.each do |num, roman|
      while i >= num
        s << roman
        i -= num
      end
    end
    s
  end
end
