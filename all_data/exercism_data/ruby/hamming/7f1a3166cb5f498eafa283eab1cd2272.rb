class Hamming
  def self.compute(a, b)
    nucleotide_pairs(a,b).count do |pair|
      mutation?(pair.first, pair.last)
    end
  end

  private

  def self.nucleotide_pairs(strand1, strand2)
    nucleotides_for(strand1).zip(nucleotides_for(strand2))
  end

  def self.nucleotides_for(strand)
    strand.split(//)
  end

  def self.mutation?(nucleotide1, nucleotide2)
    nucleotide1 && nucleotide2 &&
      nucleotide1 != nucleotide2
  end
end
