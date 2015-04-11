module RomanNumerals
  INTEGER_TO_ROMAN = {
    1    => 'I',
    5    => 'V',
    10   => 'X',
    50   => 'L',
    100  => 'C',
    500  => 'D',
    1000 => 'M'
  }
  ROMAN_VALUES = INTEGER_TO_ROMAN.keys

  def to_roman
    # http://www.roman-numerals.org/chart100.html
    roman_factors
  end

  private

  def roman_factors
    total = 0
    roman_numerals = ""
    keys_below_number.to_enum.with_index.reverse_each.map do |roman_numeral_value, idx|
      factor_count  = (self - total) / roman_numeral_value
      roman_numeral = INTEGER_TO_ROMAN[roman_numeral_value]
      if ROMAN_VALUES[idx+1]
        next_higher_roman_numeral_value = ROMAN_VALUES[idx+1]
        next_higher_roman_numeral       = INTEGER_TO_ROMAN[next_higher_roman_numeral_value]
      end
      if ROMAN_VALUES[idx-1]
        next_lower_roman_numeral_value  = ROMAN_VALUES[idx-1]
        next_lower_roman_numeral        = INTEGER_TO_ROMAN[next_lower_roman_numeral_value]
      end
      if factor_count == 4
        roman_numerals << roman_numeral + next_higher_roman_numeral
        total += next_higher_roman_numeral_value - roman_numeral_value
      elsif idx > 0 && ROMAN_VALUES[idx+1] && self - total >= ROMAN_VALUES[idx+1] - ROMAN_VALUES[idx-1]
        total += next_higher_roman_numeral_value - next_lower_roman_numeral_value
        roman_numerals << next_lower_roman_numeral << next_higher_roman_numeral
      elsif INTEGER_TO_ROMAN[factor_count] && factor_count > 1
        total += roman_numeral_value
        roman_numerals << roman_numeral
      elsif factor_count <= 3
        total += roman_numeral_value * factor_count
        roman_numerals << roman_numeral * factor_count
      elsif factor_count == roman_numeral_value
        total += roman_numeral_value
        roman_numerals << roman_numeral
      end
      break if total == self
    end
    if self - total > 0 && self - total < 4
      roman_numerals << 'I' * (self - total)
    end
    roman_numerals
  end

  def keys_below_number
    ROMAN_VALUES.select { |key| key <= self }
  end

  def nearest_numeral_above
    ROMAN_VALUES.select { |key| key > self }.first
  end
end

class Integer
  include RomanNumerals
end
