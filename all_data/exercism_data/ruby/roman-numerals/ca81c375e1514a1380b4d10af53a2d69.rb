class Roman
  BASE_NUMERALS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1_000 => 'M'
  }

  def initialize(number)
    @orig_number = number
  end

  def convert
    numerals = ''
    counter, number = @orig_number, 0
    while (counter -= number) > 0
      number = base_numeral_numbers.grep(1..counter).max
      numerals << core_numerals[number]
    end
    numerals
  end

  private

  def base_numeral_numbers
    @base_numeral_numbers_cache ||= core_numerals.keys
  end

  def core_numerals
    @base_numerals ||= BASE_NUMERALS.merge(subtractive_numerals).sort.to_h
  end

  def subtractive_numerals
    subtractive = nil
    BASE_NUMERALS.each_with_object({}) do |(number, numeral), hash|
      hash[number - subtractive[:number]] = subtractive[:numeral] + numeral if subtractive
      subtractive = { number: number, numeral: numeral } if number.to_s[0] == '1'
    end
  end
end

class Fixnum
  def to_roman
    Roman.new(self).convert
  end
end
