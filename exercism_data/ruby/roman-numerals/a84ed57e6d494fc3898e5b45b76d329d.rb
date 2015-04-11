class Integer
  def to_roman(i = 0)
    return "" if self == 0

    nums = [1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]
    roman = ["M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"]

    if self - nums[i] >= 0
      return roman[i] + (self - nums[i]).to_roman
    else
      return self.to_roman(i + 1)
    end
  end
end
