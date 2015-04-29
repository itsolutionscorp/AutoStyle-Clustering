class Integer
  def to_roman
    RomanNumeral.from_integer(self)
  end
end

class RomanNumeral < Struct.new(:integer, :letter, :subnumeral)
  def self.mappings
    one = new(1, "I", nil)
    ten = new(10, "X", one)
    hundred = new(100, "C", ten)

    [
      one,
      new(5, "V", one),
      ten,
      new(50, "L", ten),
      hundred,
      new(500, "D", hundred),
      new(1000, "M", hundred)
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

      while d.subnumber && d.prenumber_base <= integer
        numerals << d.prenumeral_string
        integer -= d.prenumber_base
      end
    end

    numerals.join
  end

  def prenumber_base
    integer - subnumber
  end

  def prenumeral_string
    subnumeral.letter + letter
  end

  def subnumber
    subnumeral && subnumeral.integer
  end

end
