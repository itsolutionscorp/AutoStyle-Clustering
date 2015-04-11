#Methods chosen were for my practice (not for legibility)

class Hamming

  def self.compute(strand_1, strand_2)
    strands_merged = strand_1.split(//).zip(strand_2.split(//)).flatten

    if strands_merged[-1] == nil
      strands_merged.pop(2)
    end

    dna_pairs = []
    while strands_merged.size > 0
      dna_pairs << strands_merged.slice!(0, 2)
    end

    counter = 0
    dna_pairs.select do |nucleotide|
      if nucleotide[0] != nucleotide[1]
        counter += 1
      end
    end
    p counter
  end
end
