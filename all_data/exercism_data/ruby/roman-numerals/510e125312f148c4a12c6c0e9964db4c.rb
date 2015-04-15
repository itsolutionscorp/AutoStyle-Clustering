class Fixnum
  @@roman_numerals = [
    [ "M", 1000],
    ["CM",  900],
    [ "D",  500],
    ["CD",  400],
    [ "C",  100],
    ["XC",   90],
    [ "L",   50],
    ["XL",   40],
    [ "X",   10],
    ["IX",    9],
    [ "V",    5],
    ["IV",    4],
    [ "I",    1]
  ]

  def to_roman
    number = self
    string = ""
    @@roman_numerals.each do |pair|
      while number / pair[1] > 0
        number -= pair[1]
        string << pair[0]
      end
    end
    string
  end
end

p 'I' == 1.to_roman
