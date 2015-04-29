class Strand
  attr_accessor :text

  def initialize(text = "")
    @text = text
  end

  def compared_with(strand)
    letters.first(strand.length).zip(strand.letters)
  end

  def letters
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
