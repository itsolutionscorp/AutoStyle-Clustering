class Fixnum
  ARABIC_TO_ROMAN = { 1_000 => "M", 500 => "D", 100 => "C", 50 => "L", 10 => "X", 
                      5 => "V", 1 => "I" }
  def to_roman
    arabic = self
    roman = ''

    arabic, roman = partly_romanize(1_000, arabic, roman)

    arabic, roman = partly_romanize(500, arabic, roman) if arabic > 0

    arabic, roman = partly_romanize(100, arabic, roman) if arabic > 0

    arabic, roman = partly_romanize(50, arabic, roman) if arabic > 0

    arabic, roman = partly_romanize(10, arabic, roman) if arabic > 0

    arabic, roman = partly_romanize(5, arabic, roman) if arabic > 0

    arabic, roman = partly_romanize(1, arabic, roman) if arabic > 0

    roman
  end

  # private

  def partly_romanize(part, arabic, roman)
    subtract, high, low = subtraction(arabic)

    if subtract == true
      roman << ARABIC_TO_ROMAN[low]
      roman << ARABIC_TO_ROMAN[high]

      new_arabic = arabic - (high - low)
    else
      times_divisible = arabic / part

      times_divisible.times do
        roman << ARABIC_TO_ROMAN[part]
      end

      new_arabic = arabic - (times_divisible * part)
    end


    [new_arabic, roman]
  end

  def subtraction(value)
    high, low = breakpoints(value)

    subtract = high / low - 1 == value / low

    [subtract, high, low]
  end

  def bounds
    upper_bounds = [5, 10, 50, 100, 500, 1000]
    lower_bounds = [1,  1, 10,  10, 100,  100]

    upper_bounds.zip(lower_bounds)
  end

  def breakpoints(value)
    high = 0
    low = 0

    bounds.each do |potential_high, potential_low|
      high = potential_high
      low = potential_low
      break if (low..high).include?(value)
    end

    [high, low]
  end
end
