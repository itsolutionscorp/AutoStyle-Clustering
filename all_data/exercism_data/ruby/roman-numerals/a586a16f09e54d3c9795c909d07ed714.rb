class Fixnum
    def to_roman
        roman_numeral = []
        numeral = self.to_s.reverse
        roman_numeral.push(roman_thousands(numeral[3]))
        roman_numeral.push(roman_hundreds(numeral[2]))
        roman_numeral.push(roman_tens(numeral[1]))
        roman_numeral.push(roman_singles(numeral[0]))
        roman_numeral.join("")
    end

   def roman_singles(unit)
    singles = {
      '0' => '',
      '1' => 'I',
      '2' => 'II',
      '3' => 'III',
      '4' => 'IV',
      '5' => 'V',
      '6' => 'VI',
      '7' => 'VII',
      '8' => 'VIII',
      '9' => 'IX'
    }
    singles[unit]
   end

   def roman_tens(unit)
    tens = {
      '0' => '',
      '1' => 'X',
      '2' => 'XX',
      '3' => 'XXX',
      '4' => 'XL',
      '5' => 'L',
      '6' => 'LX',
      '7' => 'LXX',
      '8' => 'LXXX',
      '9' => 'XC'
    }
    tens[unit]
   end

   def roman_hundreds(unit)
    hundreds = {
      '0' => '',
      '1' => 'C',
      '2' => 'CC',
      '3' => 'CCC',
      '4' => 'CD',
      '5' => 'D',
      '6' => 'DC',
      '7' => 'DCC',
      '8' => 'DCCC',
      '9' => 'CM'
    }
    hundreds[unit]
   end

   def roman_thousands(unit)
    thousands = {
      '0' => '',
      '1' => 'M',
      '2' => 'MM',
      '3' => 'MMM',
    }
    thousands[unit]
   end
 end
