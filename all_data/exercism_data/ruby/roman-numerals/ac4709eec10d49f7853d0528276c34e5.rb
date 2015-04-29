class Integer

  def to_roman
    RomanNumeralConverter.new(self).to_roman
  end
end

class RomanNumeralConverter

  def initialize(integer)
    @integer = integer
  end

  def build_arry(one, five, ten)
         ["",
          "#{one}",
          "#{one * 2}",
          "#{one * 3}",
          "#{one}#{five}",
          "#{five}",
          "#{five}#{one}",
          "#{five}#{one * 2}",
          "#{five}#{one * 3}",
          "#{one}#{ten}",]
  end

  def ones_values
    build_arry("I", "V", "X")
  end

  def tens_values
    build_arry("X", "L", "C")
  end

  def hundreds_values
    build_arry("C","D","M")
  end

  def thousands_values
    ["","M","MM","MMM"]
  end

  def to_roman
      ones = num_convert(1)
      tens = num_convert(10)
      hundreds = num_convert(100)
      thousands = num_convert(1000)

      thousands_values[thousands] + hundreds_values[hundreds] + tens_values[tens] + ones_values[ones]

  end

  def num_convert(place)
    @integer/place %10
  end

end
