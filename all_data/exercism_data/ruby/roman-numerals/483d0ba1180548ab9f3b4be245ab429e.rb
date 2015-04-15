module RomanNumerable
  NUMERALS = {
    M: 1000,
    CM: 900,
    D: 500,
    CD: 400,
    C: 100,
    XC: 90,
    L: 50,
    XL: 40,
    X: 10,
    IX: 9,
    V: 5,
    IV: 4,
    I: 1
  }

  def to_roman
    return from_numerals unless from_numerals.empty?
    init
    NUMERALS.each { |letter,_| extract(letter) }
    @@result
  end

  def init
    @@result = ""
    @@value = self
  end

  def extract(letter)
    num = NUMERALS[letter]
    while @@value >= num
      @@value = @@value - num
      @@result << letter.to_s
    end
  end

  def from_numerals(num = self)
    NUMERALS.key(num).to_s
  end

end
