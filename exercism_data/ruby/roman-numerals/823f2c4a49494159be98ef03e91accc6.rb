class Fixnum
  ARABIC_TO_ROMAN = { 1_000 => "M", 500 => "D", 100 => "C", 50 => "L",
                      10 => "X", 5 => "V", 1 => "I" }
  def to_roman
    arabic = self
    roman = ''

    ARABIC_TO_ROMAN.keys.each do |value|
      arabic, roman = partly_romanize(value, arabic, roman)
    end

    roman
  end

  private

  def partly_romanize(value, arabic, roman)
    subtract, high, low = subtraction(arabic)

    if subtract
      roman << ARABIC_TO_ROMAN[low]
      roman << ARABIC_TO_ROMAN[high]

      new_arabic = arabic - (high - low)
    else
      times_divisible = arabic / value

      times_divisible.times do
        roman << ARABIC_TO_ROMAN[value]
      end

      new_arabic = arabic - (times_divisible * value)
    end

    [new_arabic, roman]
  end

  def subtraction(arabic)
    high, low = breakpoints(arabic)

    subtract = high / low - 1 == arabic / low

    [subtract, high, low]
  end

  def bounds
    upper_bounds = [5, 10, 50, 100, 500, 1000]
    lower_bounds = [1,  1, 10,  10, 100,  100]

    upper_bounds.zip(lower_bounds)
  end

  def breakpoints(arabic)
    high = 0
    low = 0

    bounds.each do |potential_high, potential_low|
      high = potential_high
      low = potential_low
      break if (low..high).include?(arabic)
    end

    [high, low]
  end
end
