class Hamming

  def self.compute(strand1, strand2)

    strands = StringPair.new(strand1, strand2)

    strands.number_of_differences

  end
end

class StringPair
  def initialize(string1, string2)
    @string1 = string1.upcase
    @string2 = string2.upcase
  end

  def shortest_length
    [@string1.length, @string2.length].min
  end

  def equal_at?(index)
    @string1[index] == @string2[index]
  end

  def number_of_differences
    differences = 0

    (0...shortest_length).each  do  |position|
      unless  equal_at?(position)
        differences += 1
      end
    end
    differences
  end

end
