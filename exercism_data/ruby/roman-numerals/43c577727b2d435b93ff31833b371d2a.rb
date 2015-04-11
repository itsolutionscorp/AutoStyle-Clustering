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
  def roman_letters(n, previous_letters='')
    return previous_letters if n.zero?

    FACTORS_LETTERS.keys.each do |factor|
      div, mod = n.divmod(factor)
      if div > 0
        letters = previous_letters + FACTORS_LETTERS[factor] * div
        return roman_letters(mod, letters)
      end
    end
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
