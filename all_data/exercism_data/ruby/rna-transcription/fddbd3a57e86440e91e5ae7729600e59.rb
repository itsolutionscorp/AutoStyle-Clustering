class Complement
  def self.of_dna(strand)
    ComplementFinder.new(strand).dna_complements
  end

  def self.of_rna(strand)
    ComplementFinder.new(strand).rna_complements
  end
end

class ComplementFinder
  def initialize(strand)
    raise StrandNotStringError unless strand.is_a?(String)
    @nucleotide_sequence = NucleotideSequenceBuilder.new(strand).build
  end

  def dna_complements
    @nucleotide_sequence.map(&:dna_complement).join
  end

  def rna_complements
    @nucleotide_sequence.map(&:rna_complement).join
  end
end

class NucleotideSequenceBuilder
  def initialize(strand)
    @strand = strand
  end

  def build
    @strand.chars.map do |nucleotide|
      NucleotideFactory.build(nucleotide)
    end
  end
end

class NucleotideFactory
  def self.build(nucleotide)
    case nucleotide
    when 'C'
      Nucleotide::C.new
    when 'G'
      Nucleotide::G.new
    when 'T'
      Nucleotide::T.new
    when 'A'
      Nucleotide::A.new
    when 'U'
      Nucleotide::U.new
    end
  end
end

class Nucleotide
  class C
    def dna_complement
      'G'
    end
    alias_method :rna_complement, :dna_complement
  end

  class G
    def dna_complement
      'C'
    end
    alias_method :rna_complement, :dna_complement
  end

  class T
    def dna_complement
      'A'
    end
  end

  class A
    def dna_complement
      'U'
    end

    def rna_complement
      'T'
    end
  end

  class U
    def rna_complement
      'A'
    end
  end
end

class StrandNotStringError < StandardError; end
