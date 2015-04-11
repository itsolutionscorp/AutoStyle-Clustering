class Fixnum
  def to_roman
    num = self
    numeral_string = ""
    while num > 0 do
      if num >= 1000
        numeral_string += 'M'
        num -= 1000
      elsif num >= 900
        numeral_string += 'CM'
        num -= 900
      elsif num >= 500
        numeral_string += 'D'
        num -= 500
      elsif num >= 400
        numeral_string += 'CD'
        num -= 400
      elsif num >= 100
        numeral_string += 'C'
        num -= 100
      elsif num >= 90
        numeral_string += 'XC'
        num -= 90
      elsif num >= 50
        numeral_string += 'L'
        num -= 50
      elsif num >= 40
        numeral_string += 'XL'
        num -= 40
      elsif num >= 10
        numeral_string += 'X'
        num -= 10
      elsif num >= 9
        numeral_string += 'IX'
        num -= 9
      elsif num >= 5
        numeral_string += 'V'
        num -= 5
      elsif num >= 4
        numeral_string += 'IV'
        num -= 4
      elsif num >= 1
        numeral_string += 'I'
        num -= 1
      end
    end
    return numeral_string
  end
end
