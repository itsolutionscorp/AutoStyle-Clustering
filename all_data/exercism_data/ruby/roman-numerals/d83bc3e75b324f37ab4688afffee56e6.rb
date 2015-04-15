class Integer
  def to_roman
    input = self
    convert_to_roman input, ""
  end

  def convert_to_roman num, roman
    if num >= 1000
      num -= 1000
      roman << "M"
    elsif num >= 900
      num -= 900
      roman << "CM"
    elsif num >= 500
      num -= 500
      roman << "D"
    elsif num >= 400
      num -= 400
      roman << "CD"
    elsif num >= 100
      num -= 100
      roman << "C"
    elsif num >= 90
      num -= 90
      roman << "XC"
    elsif num >= 50
      num -= 50
      roman << "L"
    elsif num >= 40
      num -= 40
      roman << "XL"
    elsif num >= 10
      num -= 10
      roman << "X"
    elsif num >= 9
      num -= 9
      roman << "IX"
    elsif num >= 5
      num -= 5
      roman << "V"
    elsif num >= 4
      num -= 4
      roman << "IV"
    elsif num > 0
      num -= 1
      roman << "I"
    end

    num > 0 ? convert_to_roman(num, roman) : roman
  end
end
