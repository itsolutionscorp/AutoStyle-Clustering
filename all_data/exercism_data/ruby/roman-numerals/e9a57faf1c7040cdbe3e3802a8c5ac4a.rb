class Fixnum
  ##
  # Converts number to roman numerals for numbers
  # between 1..3999
  def to_roman
    n = self
    raise "Number out of range" if n > 3999 or n < 1
    numbers_to_numerals(n.to_s)
  end

  private
  ##
  # Recursive private function for to_roman
  def numbers_to_numerals(i)
    numerals = [
      [],          # Zero digits - Placeholder
      [ "I", "V"], # One digit
      [ "X", "L"], # Two digits
      [ "C", "D"], # Three digits
      [ "M" ]      # Four digits
    ]

    result = ""
    digits = i.size
    first = i[0].to_i
    trailing = i.slice(1, digits)

    # handle thousandths
    if digits == 4 
      result = numerals[digits][0] * first + numbers_to_numerals(trailing)
    # handle hundredths or less
    elsif digits >= 1 and digits < 4
      # act according to number
      if first <= 3
        result = numerals[digits][0] * first
      elsif first == 4
        result = numerals[digits][0] + numerals[digits][1]
      elsif first == 5
        result = numerals[digits][1]
      elsif first == 9
        result = numerals[digits][0] + numerals[digits + 1][0]
      else
        result = numerals[digits][1] + numerals[digits][0] * (first - 5)
      end 

      result += numbers_to_numerals(trailing)
    end

    return result
  end
end
