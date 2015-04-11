# An ugly first pass.
class Fixnum
  def to_roman
    RomanNumerals.notate(self)
  end
end

module RomanNumerals
  def self.notate(integer)
    arabic_numerals = integer.to_s.chars
    arabic_numerals.reverse.each_with_index.map { |numeral, position| Notation.new(position).notate(numeral) }.reverse.join
  end

  class Notation
    BASES = %w(I V X L C D M)

    def initialize(arabic_numeral_position)
      @index = arabic_numeral_position * 2
    end

    def notate(arabic_numeral)
      value = arabic_numeral.to_i

      if value == 9
        one + ten
      elsif value >= 5
        five + one * (value % 5)
      elsif value == 4
        one + five
      else
        one * value
      end
    end

    private

    def one
      BASES.slice(@index)
    end

    def five
      BASES.slice(@index + 1)
    end

    def ten
      BASES.slice(@index + 2)
    end
  end
end
