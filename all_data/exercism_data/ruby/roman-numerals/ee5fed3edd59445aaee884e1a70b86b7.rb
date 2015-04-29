# Roman numeral monkey-patch

class Fixnum
  ROMANS = { 1000 => 'M', 900 => 'CM', 500 => 'D', 400 => 'CD',
             100 => 'C', 90 => 'XC', 50 => 'L', 40 => 'XL',
             10 => 'X', 9 => 'IX', 5 => 'V', 4 => 'IV', 1 => 'I' }

  def to_roman
    value = self

    str = ''

    ROMANS.reduce( '' ) do |a, (k, v)|
      while k <= value && value > 0
        value -= k
        a += v
      end

      a
    end
  end
end
