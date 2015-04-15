class Complement

  def self.of_dna(strand)
    process(strand, DnaNucleotide).join("")
  end

  def self.of_rna(strand)
    process(strand, RnaNucleotide).join("")
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
    @@complements ||= Hash[*SEQUENCE]
  end
end

class RnaNucleotide < Nucleotide
  def complements
    @@complements ||= Hash[*SEQUENCE.reverse]
  end
end
