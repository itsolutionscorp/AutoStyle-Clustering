class Fixnum

  ROMAN_NUMBERS = { 1000 => 'M', 500 => 'D', 100 => 'C', 50 => 'L', 10 => 'X', 5 => 'V', 1 => 'I' }
  ROMAN_SUBTRACTORS = [1, 10, 100, 1000, 10000]

  def to_roman
    output = []
    remainder = self

    ROMAN_NUMBERS.keys.each do |number|

      # find whole units, eg 'XX' for 20
      if (remainder >= number) 
        multiplier = remainder / number
        output << ROMAN_NUMBERS[number] * multiplier  
        remainder -= number * multiplier
      end

      # find "subtraction"-style representations, eg 'IX' for 9 
      if (remainder < number)
        candidate_subtractors = ROMAN_SUBTRACTORS.select { |n| n < number && n != remainder }
        subtractor = candidate_subtractors.find { |n| remainder / (number - n) == 1}
        unless subtractor.nil?
          output << ROMAN_NUMBERS[subtractor] + ROMAN_NUMBERS[number]
          remainder -= number - subtractor
        end
      end

    end

    output.join
  end


  
end
