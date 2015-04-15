class Fixnum
  ROMAN_NUMERALS = ['I', 'V', 'X', 'L', 'C', 'D', 'M']

  def to_roman
    number = self
    roman = []

    place = 0
    while number != 0
      number, digit = number.divmod(10)
      ones, fives, tens = ROMAN_NUMERALS[place..place + 2]

      roman << case
      when digit == 9
        ones + tens
      when digit >= 5
        fives + ones * (digit - 5)
      when digit == 4
        ones + fives
      else
        ones * digit
      end

      place += 2
    end

    roman.reverse.join
  end
end
