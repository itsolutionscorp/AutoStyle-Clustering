class Integer
  def to_roman
    RomanNumeral.from_integer(self)
  end
end

class RomanNumeral < Struct.new(:integer, :letter, :subnumeral)
  def self.mappings
    one     = new(1,   "I", nil)
    ten     = new(10,  "X", one)
    hundred = new(100, "C", ten)

    [
      new(1000, "M", hundred),
      new(500,  "D", hundred),
      hundred,
      new(50,   "L", ten),
      ten,
      new(5,    "V", one),
      one
    ]
  end

  def self.from_integer(integer)
    mappings.reduce([]) do |acc, d|
      integer.div(d.integer).times do
        acc << d.letter
        integer -= d.integer
      end

      if d.subnumeral && (d.integer - d.subnumeral.integer) <= integer
        acc << d.subnumeral.letter + d.letter
        integer -= (d.integer - d.subnumeral.integer)
      end
      acc
    end.join
  end

end
