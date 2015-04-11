class Integer
  def to_roman
    num = 0
    roman = ''
    while num < self
      if self - num - 1000 >= 0
        roman += "M"
        num += 1000
      elsif self - num - 900 >= 0
        roman += "CM"
        num += 900
      elsif self - num - 500 >= 0
        roman += "D"
        num += 500
      elsif self - num - 400 >= 0
        roman += "CD"
        num += 400
      elsif self - num - 100 >= 0
        roman += "C"
        num += 100
      elsif self - num - 90 >= 0
        roman += "XC"
        num += 90
      elsif self - num - 50 >= 0
        roman += "L"
        num += 50
      elsif self - num - 40 >= 0
        roman += "XL"
        num += 40
      elsif self - num - 10 >= 0
        roman += "X"
        num += 10
      elsif self - num - 9 == 0
        roman += "IX"
        num += 9
      elsif self - num - 5 >= 0
        roman += "V"
        num += 5
      elsif self - num - 4 >= 0
        roman += "IV"
        num += 4
      else
        roman += "I"
        num += 1
      end
    end

    roman
  end
end
