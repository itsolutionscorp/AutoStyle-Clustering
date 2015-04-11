class Nucleotide
  def initialize(dna)
    @dna = dna
  end

  def histogram
    map = {
      'A' => 0,
      'C' => 0,
      'G' => 0,
      'T' => 0
    }

    map.keys.each do | nucleotide |
      map[nucleotide] = @dna.scan(/#{nucleotide}/).length
    end

    map
  end

  def count(nucleotide)
    histogram[nucleotide]
  end

  def self.from_dna(dna)
    if dna.match(/[^ACGT]/)
      raise ArgumentError.new("DNA is not valid :(")
    end
    new(dna)
  end
end
