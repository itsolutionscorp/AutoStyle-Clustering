class Fixnum
  NUMERALS = [["M", 1000], ["CM", 900], ["D", 500], ["CD", 400], ["C", 100], 
             ["XC", 90], ["L", 50], ["XL", 40], ["X", 10], ["IX", 9], 
             ["V", 5], ["IV", 4], ["I", 1]]

  def to_roman
    remainder = self
    NUMERALS.inject '' do |string, (numeral, base)|
      count, remainder = remainder.divmod(base)
      string + (numeral * count)
    end
  end
end
