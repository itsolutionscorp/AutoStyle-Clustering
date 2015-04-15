class Fixnum

  def to_roman
    Roman.new(self).to_s
  end
end

class Roman

  def initialize(number)
    @number = number
  end

  def to_s
    roman_letters(@number)
  end

private
  def roman_letters(n)
    letters = ''

    FACTORS_LETTERS.each do |factor, roman|
      div, mod = n.divmod(factor)
      n = mod
      letters += roman * div
    end

    letters
  end

  FACTORS_LETTERS = {
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
  }.freeze
end
