class DNA
  def initialize(strand)
    @strand = strand.split(//)
    raise ArgumentError unless valid?
  end

  def count(input)
    NucleotideOccurences.count(input, strand)
  end

  def nucleotide_counts
    Analizer.count(strand)
  end

  private

  attr_reader :strand

  def valid?
    strand.all? { |x| DNA.structure.include?(x) }
  end

  def self.structure
    ['A','T','C','G']
  end
end

class RNA
  def self.structure
    ['A','C','G','U']
  end
end

class NucleotideOccurences
  class << self
    def count(input, strand)
      raise ArgumentError unless valid?(input)
      strand.count { |nucl| nucl.eql?(input) }
    end

    def valid?(input)
      family.include?(input)
    end

    def family
      RNA.structure + DNA.structure
    end
  end
end

class Analizer
  class << self
    def count(strand)
      strand.each_with_object(base) { |nucl, stats| stats[nucl] += 1 }
    end

    def base
      { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    end
  end
end
