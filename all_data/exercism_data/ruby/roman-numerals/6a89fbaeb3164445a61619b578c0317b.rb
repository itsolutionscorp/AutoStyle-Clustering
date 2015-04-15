# For shits and giggles.
# Version of http://fawcett.blogspot.co.uk/2007/08/roman-numerals-in-haskell.html
# in ruby

class Fixnum

  romanValues = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
  romanDigits = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]
  @@romanPairs = romanValues.zip(romanDigits)

  def to_roman
    @@romanPairs.inject( ["", self] ) { |a, v|
      accumulator = a[0]
      remainder = a[1]

      (result, remainder) = remainder.divmod(v[0])
      newDigits = v[1] * result
      if remainder == 0 then
        return accumulator + newDigits
      else
        [accumulator + newDigits, remainder]
      end
    }[0]
  end

end
