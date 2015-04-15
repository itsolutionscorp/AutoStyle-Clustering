class Integer

  SYMBOL = {
    1 => {
      1 => "I", 
      5 => "V"
    },
    2 => {
      1 => "X",
      5 => "L"
    },
    3 => {
      1 => "C", 
      5 => "D"
    },
    4 => {
      1 => "M"
    }
  }

  def to_roman
    result = ""

    self.to_s.chars.each_with_index do |num, i|
      digit = self.to_s.length - i
      num = num.to_i

      result << "#{one_through_three(digit, num)}#{four(digit, num)}#{five_through_eight(digit, num)}#{nine(digit, num)}"
    end

    result
  end

  private
  def one_through_three(digit, num)
    "#{SYMBOL[digit][1] * num}" if (num > 0) & (num < 4)
  end

  def four(digit, num)
    "#{SYMBOL[digit][1]}#{SYMBOL[digit][5]}" if num == 4
  end

  def five_through_eight(digit, num)
    "#{SYMBOL[digit][5]}#{SYMBOL[digit][1] * (num - 5)}" if (num > 4) && (num < 9)
  end

  def nine(digit, num)
    "#{SYMBOL[digit][1]}#{SYMBOL[digit + 1][1]}" if num == 9
  end

end
