class Fixnum
  def to_roman
    string = ""
    case length
    when 1
      string += units_pick(self)
    when 2
      string += tens_pick(self.digits_array[0])
      string += units_pick(self.digits_array[1])
    when 3
      string += hundreds_pick(self.digits_array[0])
      string += tens_pick(self.digits_array[1])
      string += units_pick(self.digits_array[2])
    when 4
      string += thousands_pick(self.digits_array[0])
      string += hundreds_pick(self.digits_array[1])
      string += tens_pick(self.digits_array[2])
      string += units_pick(self.digits_array[3])
    end
    return string
  end

  def units_pick(num)
    unless num == 0
      ["I","II","III","IV","V","VI","VII","VIII", "IX"][num - 1]
    else 
      ""
    end
  end

  def tens_pick(num)
    unless num == 0
      ["X","XX","XXX","XL","L","LX","LXX","LXXX", "XC"][num - 1]
    else 
      ""
    end
  end

  def hundreds_pick(num)
    unless num == 0
      ["C","CC","CCC","CD","D","DC","DCC","DCCC", "CM"][num - 1]
    else 
      ""
    end
  end

  def thousands_pick(num)
    "M" * num
  end

  def length
    digits_array.length
  end

  def digits_array
    self.to_s.chars.map(&:to_i)
  end
end
