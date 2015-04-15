class Integer
  def to_roman
    romanize(self)
  end

  private

  def romanize(number)
    return ""  if number.zero?

    return "I" + romanize(number + 1)   if number ==   4  || number ==   9
    return "X" + romanize(number + 10)  if number >=  40  && number <   50
    return "X" + romanize(number + 10)  if number >=  90  && number <  100
    return "C" + romanize(number + 100) if number >= 400  && number <  500
    return "C" + romanize(number + 100) if number >= 900  && number < 1000

    { "M" => 1000, "D" => 500, "C" => 100, "L" => 50, "X" => 10, "V" => 5, "I" => 1}.each do |roman, decimal|
      return roman + romanize(number - decimal) if number >= decimal
    end
  end
end
