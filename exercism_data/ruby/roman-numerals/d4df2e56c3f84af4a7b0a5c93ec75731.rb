# class to convert decimal numbers to roman numerals
class Fixnum
  def to_roman
    thousands = {
      0 => '',
      1 => 'M',
      2 => 'MM',
      3 => 'MMM'
    }

    hundreds = {
      0 => '',
      1 => 'C',
      2 => 'CC',
      3 => 'CCC',
      4 => 'CD',
      5 => 'D',
      6 => 'DC',
      7 => 'DCC',
      8 => 'DCCC',
      9 => 'CM'
    }

    tens = {
      0 => '',
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

    units = {
      0 => '',
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

    thousand, rem = self.divmod 1000
    hundred, rem = rem.divmod 100
    ten, unit = rem.divmod 10

    "#{thousands[thousand]}#{hundreds[hundred]}#{tens[ten]}#{units[unit]}"
  end
end
