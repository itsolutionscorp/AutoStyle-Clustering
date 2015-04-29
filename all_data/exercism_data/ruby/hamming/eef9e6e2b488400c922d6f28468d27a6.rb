require 'awesome_print'

class Hamming
  def self.compute(dna1, dna2)
    return 0 if dna1 == dna2

    nucleotide_pairs(dna1, dna2).count { |n1, n2|
      n1 != n2
    }
  end

  def self.nucleotide_pairs(dna1, dna2)
    # get corresponding pairs of nucleotides
    pairs = dna1.chars.zip(dna2.chars)
    
    # only compare up to length of shortest DNA strand
    pairs = pairs.first(dna2.length)
  end
end
