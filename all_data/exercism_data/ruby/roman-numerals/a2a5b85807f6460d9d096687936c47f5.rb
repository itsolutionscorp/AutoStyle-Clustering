class Fixnum
  def to_roman
    num = self
    roman = ""
    while num > 0
      if num >= 1000
        roman << 'M'
        num -= 1000
      elsif num >= 500
        roman << 'D'
        num -= 500
      elsif num >= 100
        roman << 'C'
        num -= 100
      elsif num >= 50
        roman << 'L'
        num -= 50
      elsif num >= 10
        roman << 'X'
        num -= 10
      elsif num >= 5
        roman << 'V'
        num -= 5
      elsif num >= 1
        roman << 'I'
        num -= 1
      end
    end
    roman
  end

end
