class Fixnum
  def to_roman
    tmp = self
    CONVERT.map do |arabic, roman|
      # mult = tmp / arabic
      # tmp -= arabic * mult
      roman * digit_at(arabic)
    end.join
  end

  private

  def digit_at rank
    self / rank % 10
  end

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
    1 => 'I',
  }

end
