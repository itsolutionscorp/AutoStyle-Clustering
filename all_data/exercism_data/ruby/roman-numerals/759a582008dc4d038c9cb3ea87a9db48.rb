module RomanMap

  def thousands_map
    thousands = {
      1 => 'M',
      2 => 'MM',
      3 => 'MMM'
    }
  end

  def hundreds_map
    hundreds = {
      1 => 'C',
      2 => 'CC',
      3 => 'CCC',
      4 => 'CD',
      5 => 'D',
      6 => 'DC',
      7 => 'DCC',
      8 => 'DCC',
      9 => 'CM'
    }
  end

  def tens_map
    tens = {
      1 => 'X',
      2 => 'XX',
      3 => 'XXX',
      4 => 'XL',
      5 => 'L',
      6 => 'LX',
      7 => 'LXX',
      8 => 'LXXX',
      9 => 'XC'
    }
  end

  def ones_map
    ones = {
      1 => 'I',
      2 => 'II',
      3 => 'III',
      4 => 'IV',
      5 => 'V',
      6 => 'VI',
      7 => 'VII',
      8 => 'VIII',
      9 => 'IX'
    }
  end

end
