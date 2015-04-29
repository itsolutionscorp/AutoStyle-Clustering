class Fixnum
  ROMAN_MAPPINGS = {
    1 => 'I',
    5 => 'V',
    10 => 'X',
    50 => 'L',
    100 => 'C',
    500 => 'D',
    1000 => 'M',
  }

  EXCEPTIONS = {
    /IIII/ => 'IV',
    /VIV/ => 'IX',
    /XXXX/ => 'XL',
    /LXL/ => 'XC',
    /CCCC/ => 'CD',
    /DCD/ => 'CM',
  }

  def to_roman
    input_number = self
    roman_numeral = ''
    until input_number == 0
      ROMAN_MAPPINGS.reverse_each do |numeral, roman_letter|
        if input_number % numeral == 0
          input_number -= numeral
          roman_numeral.prepend(roman_letter)
          break
        end
      end
    end

    check_for_exceptions(roman_numeral)
  end

  private

  def check_for_exceptions(roman_numeral)
    EXCEPTIONS.each do |exception, substitution|
      roman_numeral.gsub!(exception, substitution)
    end
    roman_numeral
  end
end
