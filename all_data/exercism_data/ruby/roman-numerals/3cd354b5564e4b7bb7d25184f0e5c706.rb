class Fixnum
  def to_roman
    array = self.to_s.chars.reverse
    result = ''
    array.length.times { |i|
      result = (send("roma_#{i.to_s}", array[i].to_i)).concat(result)
    }
    result
  end

  private
  def roma_0(num)
    case num
    when 1
      'I'
    when 2
      'II'
    when 3
      'III'
    when 4
      'IV'
    when 5
      'V'
    when 6
      'VI'
    when 7
      'VII'
    when 8
      'VIII'
    when 9
      'IX'
    when 0
      '' # nothing
    end
  end

  def roma_1(num)
    case num
    when 1
      'X'
    when 2
      'XX'
    when 3
      'XXX'
    when 4
      'XL'
    when 5
      'L'
    when 6
      'LX'
    when 7
      'LXX'
    when 8
      'LXXX'
    when 9
      'XC'
    when 0
      '' # 
    end
  end

  def roma_2(num)
    case num
    when 1
      'C'
    when 2
      'CC'
    when 3
      'CCC'
    when 4
      'CD'
    when 5
      'D'
    when 6
      'DC'
    when 7
      'DCC'
    when 8
      'DCCC'
    when 9
      'CM'
    when 0
      '' # 
    end
  end

  def roma_3(num)
    case num
    when 1
      'M'
    when 2
      'MM'
    when 3
      'MMM'
    when 4
      'MV'
    when 5
      'V'
    when 0
      '' # 
    end
  end


end
