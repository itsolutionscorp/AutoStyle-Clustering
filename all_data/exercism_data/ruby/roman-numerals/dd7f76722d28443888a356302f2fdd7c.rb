class Integer
  def to_roman
    RomanNumeral.from_integer(self)
  end
end

class RomanNumeral < Struct.new(:integer, :letter, :subnumber)
  def self.mappings
    [
      new(1, "I", 0),
      new(5, "V", 1),
      new(10, "X", 1),
      new(50, "L", 10),
      new(100, "C", 10),
      new(500, "D", 100),
      new(1000, "M", 100)
    ]
  end

  def self.mappings_desc
    mappings.sort_by(&:integer).reverse
  end

  def self.from_integer(integer)
    numerals = []

    mappings_desc.each do |d|
      div, mod = integer.divmod d.integer

      numerals += div.times.map { d.letter }
      integer -= d.integer * div

      while d.subnumber && (d.integer - d.subnumber <= integer)
        numerals += [d.subnumeral.letter, d.letter]
        integer -= (d.integer - d.subnumber)
      end
    end
    numerals.join
  end


  def subnumeral
    self.class.mappings.detect { |r| r.integer == subnumber }
  end

end
