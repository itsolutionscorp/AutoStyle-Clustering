class Fixnum
  def to_roman
    num1 = self
    string1 = 'M' * num1.divmod(1000)[0]
    num2 = num1.divmod(1000)[1]
    if num2 >= 900
      string2 = 'CM'
      num2 -= 900
    else
      string2 = 'D' * num2.divmod(500)[0]
    end
    num3 = num2.divmod(500)[1]
    if num3 >= 400
      num3 -= 400
      string3 = 'CD'
    else
      string3 = 'C' * num3.divmod(100)[0]
    end
    num4 = num3.divmod(100)[1]
    if num4 >= 90
      num4 -= 90
      string4 = 'XC'
    else
      string4 = 'L' * num4.divmod(50)[0]
    end
    num5 = num4.divmod(50)[1]
    if num5 >= 40
      num5 -= 40
      string5 = 'XL'
    else
      string5 = 'X' * num5.divmod(10)[0]
    end
    num6 = num5.divmod(10)[1]
    if num6 == 9
      num6 -= 9
      string6 = 'IX'
    else
      string6 = 'V' * num6.divmod(5)[0]
    end
    if num6.divmod(5)[1] == 4
      string7 = 'IV'
    else
      string7 = 'I' * num6.divmod(5)[1]
    end
    string8 = string1.concat(string2).concat(string3).concat(string4).concat(string5).concat(string6).concat(string7)
    puts "#{self} = #{string8} "
    string8
  end


end
