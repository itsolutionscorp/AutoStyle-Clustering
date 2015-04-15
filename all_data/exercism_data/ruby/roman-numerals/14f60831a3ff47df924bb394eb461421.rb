class Integer

  def to_roman
    num = self
    str = ''

    while num > 0

      if num >= 1000
        str += 'M'
        num -= 1000
      elsif num >= 900
        str += 'CM'
        num -= 900
      elsif num >= 500
        str += 'D'
        num -= 500
      elsif num >= 400
        str += 'CD'
        num -= 400
      elsif num >= 100
        str += 'C'
        num -= 100
      elsif num >= 90
        str += 'XC'
        num -= 90
      elsif num >= 50
        str += 'L'
        num -= 50
      elsif num >= 40
        str += 'XL'
        num -= 40
      elsif num >= 10
        str += 'X'
        num -= 10
      elsif num == 9
        str += 'IX'
        num -= 9
      elsif num >= 5
        str += 'V'
        num -= 5
      elsif num == 4
        str += 'IV'
        num -= 4
      else
        str += 'I'
        num -= 1
      end

    end

    str
  end

end
