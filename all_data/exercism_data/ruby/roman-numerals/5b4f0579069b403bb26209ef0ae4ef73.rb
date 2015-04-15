class Fixnum
  def to_roman
    thousands + hundreds + tens + ones
  end

private
  def ones
    ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX'][self % 10]
  end

  def tens
    ['', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC'][(self % 100)/10]
  end

  def hundreds
    ['', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM'][(self % 1000)/100]
  end

  def thousands
    ['', 'M', 'MM', 'MMM'][(self % 10000)/1000]
  end
end
