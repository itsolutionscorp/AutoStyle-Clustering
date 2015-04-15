class Integer
  def to_roman
    RomanNumeral.from_integer(self)
  end
end

class RomanNumeral < Struct.new(:integer, :letter, :sub_numbers)
  def self.mappings
    [
      new(1, "I", []),
      new(5, "V", [1]),
      new(10, "X", [1]),
      new(50, "L", [1, 5, 10]),
      new(100, "C", [1, 5, 10]),
      new(500, "D", [1, 5, 10, 50, 100]),
      new(1000, "M", [1, 5, 10, 50, 100])
    ]
  end

  def self.mappings_desc
    mappings.sort_by(&:integer).reverse
  end

  def self.from_integer(integer)
    numerals = []

    mappings_desc.each do |d|
      until integer < d.integer
        numerals << d.letter
        integer -= d.integer
      end

      while sn = d.sub_numeral_for(integer)
        numerals += [sn.letter, d.letter]
        integer -= (d.integer - sn.integer)
      end
    end
    numerals.join
  end

  def sub_numeral_for(int)
    sub_numerals.sort_by(&:integer).reverse.detect do |n|
      (self.integer - n.integer) <= int
    end
  end

  def sub_numerals
    self.class.mappings.select { |m| sub_numbers.include? m.integer }
  end

end
