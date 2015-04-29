class Fixnum
    DIGITS = {1 => 'I', 5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M'}

  def to_roman
    greedy(self)
  end

  private

  def greedy(num)
    reversed_roman = DIGITS.keys.reverse
    numeral = ''

    # loop through DIGITS key values in descending order
    # [1000, 500, 100, 50, 10, 5, 1]
    reversed_roman.each_index do |index|

      if reversed_roman.include?(reversed_roman[index+1]) && num/reversed_roman[index] == 0 && num/reversed_roman[index+1] > 1
        # 4 / 5 => 0
        # 4 / (5-1) == 1 => 'IV'
        if num/(reversed_roman[index]-reversed_roman[index+1]) >0 
          numeral += DIGITS[reversed_roman[index+1]] + DIGITS[reversed_roman[index]]
          num -= (reversed_roman[index]-reversed_roman[index+1])
        end

      elsif reversed_roman.include?(reversed_roman[index+2]) && num/reversed_roman[index] == 0 && num/reversed_roman[index+2] >= 1
        # 4 / 5 => 0
        # 4 / (5-1) == 1 => 'IV'
        if num/(reversed_roman[index]-reversed_roman[index+2]) > 0
          numeral += DIGITS[reversed_roman[index+2]] + DIGITS[reversed_roman[index]]
          num -= (reversed_roman[index]-reversed_roman[index+2])
        end
      end

      # 6/10 => 0, skip
      # 6/5 => 1
      # 1/1 => 1
      while num/reversed_roman[index] > 0
        numeral += DIGITS[reversed_roman[index]]
        num -= reversed_roman[index]
      end
    end

    return numeral

  end
end
