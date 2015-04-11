class Fixnum
  
  NUMERALS = {
    I: 1,
    IV: 4,
    V: 5,
    IX: 9,
    X: 10,
    XL: 40,
    L: 50,
    XC: 90,
    C: 100,
    CD: 400,
    D: 500,
    CM: 900,
    M: 1000
  }

  def to_roman
    number = self
    numeral = ""
    
    NUMERALS.values.reverse.each do |n|
      div_result, mod = number.divmod(n)
      numeral << NUMERALS.key(n).to_s * div_result
      number = mod
    end
    numeral
  end

end
