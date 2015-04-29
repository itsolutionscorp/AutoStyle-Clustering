class Fixnum

  def deci_to_roman_hash
    {
      0   => '',
      1   => 'I',
      5   => 'V',
      10  => 'X',
      50  => 'L',
      100 => 'C',
      500 => 'D',
      1000=> 'M'
    }
  end

  def to_roman
    digit = self.to_s.chars.to_a #to_a is need for ruby version 1.9.x
    result = ''

    (digit.length - 1).downto(0) do |i|
      result << digit1((10 ** i), digit[(digit.length - 1) - i].to_i)
    end

    result
  end

  private
  def digit1(digit, num)
    str = ''

    rest = num % 5
    div = (num / 5).to_i
    if rest == 0
      str << deci_to_roman_hash[((5 * div) * digit)]      
    elsif rest == 4
      str << (deci_to_roman_hash[(1 * digit)] + deci_to_roman_hash[((5 * (div + 1)) * digit)])
    else
      str << deci_to_roman_hash[(5 * digit)] if num > 5
      rest.times { |i|
          str << deci_to_roman_hash[(1 * digit)]
      }
    end
    str
  end
end
