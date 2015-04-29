class StrandNormalizer
  def normalize(original, copy)
    comparison = original.length <=> copy.length

    strands = nil
    case comparison
    when -1
      strands = StrandCollection.new(original, copy.slice(0, original.length))
    when 1
      strands = StrandCollection.new(original.slice(0, copy.length), copy)
    else
      strands = StrandCollection.new(original, copy)
    end
  end
end

class Strand
  attr_reader :nucleotides

  def initialize(strand_text)
    @nucleotides = []
    @nucleotides = strand_text.chars
  end
end

class StrandCollection
  attr_reader :original, :copy

  def initialize(original, copy)
    @original = Strand.new(original)
    @copy = Strand.new(copy)
  end

  def distance
    return 0 if original.nucleotides.length == 0 && copy.nucleotides.length == 0

    calculate_distance
  end

  private

  def calculate_distance
    distance = 0
    original.nucleotides.each_with_index do |n, i|
      distance += 1 unless n == copy.nucleotides[i]
    end

    distance
  end
end

class Hamming
  def self.compute(original, copy)
    @normalizer = StrandNormalizer.new
    strands = @normalizer.normalize(original, copy)

    strands.distance
  end
end
