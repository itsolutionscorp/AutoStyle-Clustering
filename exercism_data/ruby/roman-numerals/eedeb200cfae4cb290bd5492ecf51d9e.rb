class Fixnum

  def to_roman
    i = 10
    string = ""
    while self >= i / 10
      digit = self % i - self % (i/10)
      i *= 10
      string = roman_helper(digit) + string
    end
    string
  end

  def roman_helper (n)
    if  n < 4
      return "I"*n
    elsif n == 4
      return "IV"
    elsif n == 5
      return "V"
    end

    if n < 9
      return "V" + "I"*(n-5)
    elsif n == 9
      return "IX"
    elsif n % 10 == 0 && n < 40
      return "X"*(n/10)
    end

    if n == 40
      return "XL"
    elsif n == 50
      return "L"
    elsif n >= 60 and n < 90
      return "L" + "X"*((n-50)/10)
    end

    if n == 90
      return "XC"
    elsif n % 100 == 0 && n < 400
      return "C"*(n/100)
    elsif n == 400
      return "CD"
    elsif n < 900
      return "D" + "C"*((n-500)/100)
    end

    if n == 900
      return "CM"
    elsif n % 1000 == 0
      return "M"*(n/1000)
    end

  end

end
