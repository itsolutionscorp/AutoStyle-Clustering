class Numeric

  ROMAN_NUMERALS = {
    10 =>    [ 'I', 'V', 'X' ],
    100 =>   [ 'X', 'L', 'C' ],
    1000 =>  [ 'C', 'D', 'M' ],
    10000 => [ 'M' ]
  }

  def to_roman
    roman_numeral = ''
    base = 1

    while self >= base do
      digit = (self / base) % 10
      base *= 10
      numerals = ROMAN_NUMERALS[base]

      roman_numeral.insert(0, case digit
                              when 1..3 then numerals[0] * digit
                              when 4    then numerals[0] + numerals[1]
                              when 5    then numerals[1]
                              when 6..8 then numerals[1] + numerals[0] * (digit - 5)
                              when 9    then numerals[0] + numerals[2]
                              when 10   then numerals[2]
                              else ''
                              end)
    end

    roman_numeral
  end

end
