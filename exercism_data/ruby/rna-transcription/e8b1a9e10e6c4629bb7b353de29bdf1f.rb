class Complement

  def self.of_dna(strand)
    klass = DnaNucleotide
    process(strand, klass).join("")
  end

  def self.of_rna(strand)
    klass = RnaNucleotide
    process(strand, klass).join("")
  end

  private

  def self.process(strand, klass)
    strand.split("").map do |s|
      klass.new(s).complement
    end
  end
end

class Nucleotide < String
  SEQUENCE = ['G', 'C', 'C', 'G', 'T', 'A', 'A', 'U']

  def complement
    complements[self]
  end

  def complements
  end
end

class DnaNucleotide < Nucleotide
  def complements
    Hash[*SEQUENCE]
  end
end

class RnaNucleotide < Nucleotide
  def complements
    Hash[*SEQUENCE.reverse]
  end
end
