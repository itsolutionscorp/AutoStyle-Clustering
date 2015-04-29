ROMAN_NUMERALS = [
                  ["M",  1000],
                  ["CM", 900],
                  ["D",  500],
                  ["CD", 400],
                  ["C",  100],
                  ["XC", 90],
                  ["L",  50],
                  ["XL", 40],
                  ["X",  10],
                  ["IX", 9],
                  ["V",  5],
                  ["IV", 4],
                  ["I",  1]
                 ].freeze

class Converter
  def initialize(remaining, converted)
    @remaining = remaining # 102
    @converted = converted # ''
  end

  def roman_numerals
    @converted
  end

  def convert(numeral, value) # 102
    while @remaining - value >= 0
      @remaining = @remaining - value
      @converted << numeral # 'CII'
    end
  end
end

class Fixnum
  def to_roman
    c = Converter.new(self, '')
    ROMAN_NUMERALS.each do |roman_numeral|
      c.convert(roman_numeral[0], roman_numeral[1])
    end
    c.roman_numerals
  end
end
