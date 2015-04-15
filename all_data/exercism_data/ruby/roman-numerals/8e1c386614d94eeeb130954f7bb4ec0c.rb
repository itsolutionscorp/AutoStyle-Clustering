module RomanNumerals
  class ConvertToRoman
    attr_reader :n

    def initialize(n)
      @n = n
    end

    def convert
      numerals.join
    end

    private

    def components
      [1000, 100, 10, 1].map do |d|
        component(d)
      end
    end

    def component(d)
      d * (n % (10*d) / d)
    end

    def numerals
      components.map {|c| numeral_for(c) }
    end

    NUMERALS = {
      0    => "",
      1    => "I",
      4    => "IV",
      5    => "V",
      9    => "IX",
      10   => "X",
      40   => "XL",
      50   => "L",
      90   => "XC",
      100  => "C",
      400  => "CD",
      500  => "D",
      900  => "CM",
      1000 => "M",
    }
    
    def numeral_for(c)
      NUMERALS.fetch(c){ infer_numeral_for(c) }
    end

    def infer_numeral_for(c)
      base, rn = NUMERALS.to_a.reverse.detect {|x, numeral| x < c }
      rn + numeral_for(c - base)
    end
  end
end

class Integer
  def to_roman
    RomanNumerals::ConvertToRoman.new(self).convert
  end
end
