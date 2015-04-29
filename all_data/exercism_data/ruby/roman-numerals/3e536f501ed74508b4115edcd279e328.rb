class Numeric
  def to_roman
    roman = ""; decimal = self

    (decimal / 1000).times {roman << 'M'}; decimal %= 1000

    if decimal >= 900
      roman << 'CM'; decimal -= 900
    else
      (decimal / 500).times {roman << 'D'}; decimal %= 500
    end

    if decimal >= 400
      roman << 'CD'; decimal -= 400
    else
      (decimal / 100).times {roman << 'C'}; decimal %= 100
    end

    if decimal >= 90
      roman << 'XC'; decimal -= 90
    else
      (decimal / 50).times {roman << 'L'}; decimal %= 50
    end

    if decimal >= 40
      roman << 'XL'; decimal -= 40
    else
      (decimal / 10).times {roman << 'X'}; decimal %= 10
    end

    if decimal ==  9
      roman << 'IX'
    elsif decimal == 4
      roman << 'IV'
    else
      (decimal / 5).times {roman << 'V'}; decimal %= 5
      (decimal / 1).times {roman << 'I'}; decimal %= 1
    end

    return roman
  end
end
