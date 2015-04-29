class Fixnum

  def to_roman
    number = self
    m = "M" * (number / 1000)
    number = number - (1000 * (number / 1000))
    mc = "CM" * (number / 900)
    number = number - (900 * (number / 900))
    d = "D" * (number / 500)
    number = number - (500 * (number / 500))
    cd = "CD" * (number / 400)
    number = number - (400 * (number / 400))
    c = "C" * (number / 100)
    number = number - (100 * (number / 100))
    xc = "XC" * (number / 90)
    number = number - (90 * (number / 90))
    l = "L" * (number / 50)
    number = number - (50 * (number / 50))
    xl = "XL" * (number / 40)
    number = number - (40 * (number / 40))
    x = "X" * (number / 10)
    number = number - (10 * (number / 10))
    ix = "IX" * (number / 9)
    number = number - (9 * (number / 9))
    v = "V" * (number / 5)
    number = number - (5 * (number / 5))
    iv = "IV" * (number / 4)
    number = number - (4 * (number / 4))
    i = "I" * (number / 1)
    p m + mc + d + cd + c + xc + l + xl + x + ix+ v + iv + i
  end

end

2500.to_roman
