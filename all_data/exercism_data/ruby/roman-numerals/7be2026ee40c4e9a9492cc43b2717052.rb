class Fixnum

  def to_roman
    value = self
    String.new.tap do |result|
      ROMAN.sort.reverse.each do |arabic, roman|
        repeats = value / arabic
        value = value - (repeats * arabic)
        result << roman * repeats
      end
    end
  end

  ROMAN = {
    1    => 'I',
    4    => 'IV',
    5    => 'V',
    9    => 'IX',
    10   => 'X',
    40   => 'XL',
    50   => 'L',
    90   => 'XC',
    100  => 'C',
    400  => 'CD',
    500  => 'D',
    900  => 'CM',
    1000 => 'M'
  }
end
