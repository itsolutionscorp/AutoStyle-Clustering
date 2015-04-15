Strand = Struct.new(:text) do
  def compared_with(strand)
    characters.first(strand.length).zip(strand.characters)
  end

  def characters
    text.split ""
  end

  def length
    text.length
  end
end

class Hamming
  class << self
    def compute(x, y)
      strand1 = Strand.new x
      strand2 = Strand.new y

      strand1
        .compared_with(strand2)
        .reject {|a, b| a == b }
        .count
    end
  end
end
