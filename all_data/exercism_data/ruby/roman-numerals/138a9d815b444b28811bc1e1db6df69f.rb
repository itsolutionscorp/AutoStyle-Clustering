class Integer < Numeric

  def to_roman
      roman = "" #output placeholder
      arabic = self

      #Hash of numeric values the number can be split into and their corresponding string values
      numbers = {1000 => "M", 900 => "CM", 500 => "D", 400 => "CD", 100 => "C", 90 => "XC",
                50 => "L", 40 =>"XL", 10 => "X", 9 => "IX", 5 => "V", 4 => "IV", 1  => "I"}

      numbers.each do |a,r| #for each number above
        while arabic >= a #if the arabic numeral value is still large enough to contain the key
          roman += r #add the string to the output variable
          arabic -= a #remove the numeric value and repeat until the key is larger than the arabic numeral value
        end #move on to the next smallest number in the hash
      end

    roman
  end

end

