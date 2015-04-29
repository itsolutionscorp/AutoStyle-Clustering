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
    counter = @orig_number
    numerals.each_with_object('') do |(number, numeral), roman|
      roman << numeral * (counter / number)
      counter = counter % number
    end
  end

  private

  def numerals
    BASE_NUMERALS.merge(subtractive_numerals).sort.reverse.to_h
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
