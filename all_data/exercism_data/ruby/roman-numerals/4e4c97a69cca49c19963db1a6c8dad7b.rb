class Integer

  NUMERALS = [[1000, "M"], [500, "D"],
                    [100, "C"], [50, "L"],
                    [10, "X"], [5, "V"], [1, "I"]]

  def to_roman
    number = self
    roman = ""

    NUMERALS.each_with_index do |pair,i|
      if number/pair[0] == 1 && ((number - pair[0])/NUMERALS[i+1][0] == 4 if NUMERALS[i+1])
        roman << NUMERALS[i+1][1] << NUMERALS[i-1][1]
        number = number - pair[0] - 4*NUMERALS[i+1][0]
      elsif number/pair[0] == 4
        roman << pair[1] << NUMERALS[i-1][1]
        number = number - pair[0]*(number/pair[0])
      else
        (number/pair[0]).times do
          roman << pair[1]
          number = number - pair[0]
        end
      end
      return roman if number == 0
    end
  end

end
