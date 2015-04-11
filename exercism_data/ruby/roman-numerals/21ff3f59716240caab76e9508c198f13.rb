public
def to_roman
  numerals = [
    ["M", 1000],
    ["CM", 900],
    ["D", 500],
    ["CD", 400],
    ["C", 100],
    ["XC", 90],
    ["L", 50],
    ["XL", 40],
    ["X", 10],
    ["IX", 9],
    ["V", 5],
    ["IV", 4],
    ["I", 1]
  ]

  number = self
  result = ''

  numerals.each do |roman, arabic|
    while number >= arabic
      result += roman
      number -= arabic
    end
  end
  result

end
