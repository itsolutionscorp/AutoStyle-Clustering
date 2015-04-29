class Fixnum
  def to_roman
    case true
    when self == 0
      return ""
    when self >= 1000
      return "M" + (self - 1000).to_roman
    when self >= 900
      return "CM" + (self - 900).to_roman
    when self >= 500
      return "D" + (self - 500).to_roman
    when self >= 400
      return "CD" + (self - 400).to_roman
    when self >= 100
      return "C" + (self - 100).to_roman
    when self >= 90
      return "XC" + (self - 90).to_roman
    when self >= 50
      return "L" + (self - 50).to_roman
    when self >= 40
      return "XL" + (self - 40).to_roman
    when self >= 30
      return "XXX" + (self - 30).to_roman
    when self >= 10
      return "X" + (self - 10).to_roman
    when self == 9
      return "IX"
    when self >= 5
      return "V" + (self - 5).to_roman
    when self == 4
      return "IV"
    when self <= 3
      return "I" * self
    end
  end
end
