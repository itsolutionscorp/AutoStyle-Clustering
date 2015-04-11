module Roman
  def to_roman
    RomanConverter.new(self).convert
  end

  class RomanConverter
    def initialize(number)
      @number = number
    end

    attr_accessor :number

    def convert
      [
        convert_decade(1000, 'M', '',  ''),
        convert_decade( 100, 'C', 'D', 'M'),
        convert_decade(  10, 'X', 'L', 'C'),
        convert_decade(   1, 'I', 'V', 'X')
      ].join
    end

    def convert_decade(step, char_one, char_five, char_ten)
      count = count_for(step)
      if count == 0
        ''
      elsif count <= 3
        char_one * count
      elsif count == 4
        char_one + char_five
      elsif count == 5
        char_five
      elsif count <= 8
        char_five + char_one * (count - 5)
      else
        char_one + char_ten
      end
    end

    def count_for(step)
      count = number / step
      self.number -= count * step
      count
    end
  end
end

# Monkey-patch Ruby Numeric to include Roman numeral conversion
class Numeric
  include Roman
end
