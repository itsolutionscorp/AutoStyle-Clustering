class Fixnum
  # First draft

  ROMAN = {
    1000 => 'M',
     500 => 'D',
     100 => 'C',
      50 => 'L',
      10 => 'X',
       5 => 'V',
       1 => 'I'
  }

  def to_roman
    places = to_s.length

    roman_string = ""

    to_s.split("").each_with_index do |numeral, index|
      power = places - index

      case power
      when 4
        case numeral
        when '1', '2', '3'
          roman_string << ('M' * numeral.to_i)
        end
      when 3
        case numeral
        when '1', '2', '3'
          roman_string << ('C' * numeral.to_i)
        when '4'
          roman_string << 'CD'
        when '5'
          roman_string << 'D'
        when '6', '7', '8'
          roman_string << 'D' << ('C' * (numeral.to_i - 5))
        when '9'
          roman_string << 'CM'
        end
      when 2
        case numeral
        when '1', '2', '3'
          roman_string << ('X' * numeral.to_i)
        when '4'
          roman_string << 'XL'
        when '5'
          roman_string << 'L'
        when '6', '7', '8'
          roman_string << 'L' << ('X' * (numeral.to_i - 5))
        when '9'
          roman_string << 'XC'
        end
      when 1
        case numeral
        when '1', '2', '3'
          roman_string << ('I' * numeral.to_i)
        when '4'
          roman_string << 'IV'
        when '5'
          roman_string << 'V'
        when '6', '7', '8'
          roman_string << 'V' << ('I' * (numeral.to_i - 5))
        when '9'
          roman_string << 'IX'
        end
      end
    end

    roman_string
  end
end
