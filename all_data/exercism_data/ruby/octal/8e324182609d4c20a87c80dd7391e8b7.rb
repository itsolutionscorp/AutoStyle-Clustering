class Octal
    def initialize(octal)
      @octal = octal.to_s.match(/[^0-7]/) ? 0 : octal
    end

    def to_decimal
      @octal.to_s.reverse.chars.each_with_index.reduce(0) do |sum, (char, index)|
        sum += decimal_value_of_octal_digit(char.to_i, index)
      end
    end

    private
    def decimal_value_of_octal_digit(digit, column)
      digit == 0 ? 0 : (8**column) * digit
    end
end
