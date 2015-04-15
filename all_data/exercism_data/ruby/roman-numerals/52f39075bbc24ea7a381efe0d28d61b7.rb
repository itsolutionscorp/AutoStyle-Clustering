class Integer
  class Roman
    DIGITS = {
      1    => 'I',
      5    => 'V',
      10   => 'X',
      50   => 'L',
      100  => 'C',
      500  => 'D',
      1000 => 'M'
    }

    attr_reader :int

    def initialize(int)
      @int = int
    end

    def to_s
      numerals = []
      num = int
      place = 1
      while digit = lowest_digit(num) do
        numerals.unshift digit_to_roman(digit, place)
        num = truncate_lowest_digit(num)
        place += 1
        break if num == 0
      end
      numerals.join
    end

    def lowest_digit(num)
      num % 10
    end

    def truncate_lowest_digit(num)
      num / 10
    end

    def digit_to_roman(digit, place)
      ones = ones_numeral(place)
      fives = fives_numeral(place)
      tens = tens_numeral(place)

      if digit < 4
        ones * digit
      elsif digit == 4
        ones + fives
      elsif digit == 5
        fives
      elsif digit > 5 && digit < 9
        fives + (ones * (digit - 5))
      else
        ones + tens
      end
    end

    def ones_numeral(place)
      get_numeral(1, place)
    end

    def fives_numeral(place)
      get_numeral(5, place)
    end

    def tens_numeral(place)
      get_numeral(10, place)
    end

    def get_numeral(base, place)
      padding = '0' * (place - 1)
      key = "#{base}#{padding}".to_i
      DIGITS[key]
    end
  end

  def to_roman
    Roman.new(self).to_s
  end
end
