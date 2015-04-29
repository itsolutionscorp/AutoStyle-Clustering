module RomanNumerals
  extend self

  def to_roman(number)
    symbols.inject('') do |result, threshold|
      value, symbol = threshold
      occurrences = number < value ? 0 : number / value
      number -= value * occurrences
      result << symbol * occurrences
    end
  end

  protected

  def symbols
    {
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
  end
end

class Fixnum
  def to_roman
    RomanNumerals.to_roman self
  end
end
