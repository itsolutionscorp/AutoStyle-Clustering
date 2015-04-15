module Roman
  ROMANS = {
    1000 => 'M',
    500 => 'D',
    100 => 'C',
    50 => 'L',
    10 => 'X',
    5 => 'V',
    1 => 'I'
  }

  def to_roman
    n = self
    result = ''
    ROMANS.each do |number, letter|
      count = n / number
      if count < 4
        result += letter * count
      elsif result[-1] == ROMANS[number * 5]
        result = result.slice(0..-2)
        result += letter + ROMANS[number * 10]
      else
        result += letter + ROMANS[number * 5]
      end
      n -= count * number
    end
    result
  end
end

class Fixnum
  include Roman
end
