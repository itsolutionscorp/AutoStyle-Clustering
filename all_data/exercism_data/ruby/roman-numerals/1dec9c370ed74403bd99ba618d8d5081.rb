class Numeric

  def to_roman
    convert
  end

  def convert
    str = []
    self.to_s.split(//).reverse.each_with_index do |c, i|
      # for each char
      # check its position: thousands? hundreds? tens?
      case i
        when 0
          str.unshift units(c)
        when 1
          str.unshift tens(c)
        when 2
          str.unshift hundreds(c)
        when 3
          str.unshift thousands(c)
      end
    end
    str.join
  end

  def thousands(num)
    value = num.to_i
    return value.times.map { 'M' }.join # no need to go above 3000
  end

  def hundreds(num)
    value = num.to_i
    str = ""
    while value > 0 do
      if value.eql? 9
        value -= 9
        str << 'CM'
      elsif value - 5 >= 0
        value -= 5
        str << 'D'
      elsif value.eql? 4
        value -= 4
        str << 'CD'
      else
        str << value.times.map { 'C' }.join
        break
      end
    end
    str
  end

  def tens(num)
    value = num.to_i
    str = ""
    while value > 0 do
      if value.eql? 9
        value -= 9
        str << 'XC'
      elsif value - 5 >= 0
        value -= 5
        str << 'L'
      elsif value.eql? 4
        value -= 4
        str << 'XL'
      else
        str << value.times.map { 'X' }.join
        break
      end
    end
    str
  end

  def units(num)
    case num
      when '1'
        'I'
      when '2'
        'II'
      when '3'
        'III'
      when '4'
        'IV'
      when '5'
        'V'
      when '6'
        'VI'
      when '7'
        'VII'
      when '8'
        'VIII'
      when '9'
        'IX'
    end
  end

end
