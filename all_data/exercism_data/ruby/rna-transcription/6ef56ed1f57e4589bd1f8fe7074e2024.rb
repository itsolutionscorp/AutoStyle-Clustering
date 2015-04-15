class Complement
  def self.of_dna(dna_strand)
    dna = Strand.new(dna_strand)
    dna.convert_strand_to_rna(dna_strand)
  end

  def self.of_rna(rna_strand)
    rna = Strand.new(rna_strand)
    rna.convert_strand_to_dna(rna_strand)
  end
end

class Strand
attr_accessor :strand

  def initialize(strand)
    @strand = strand
  end

  def convert_strand_to_rna(dna_strand)
    rna_strand = []

    dna_strand.split(//).each do |acid|
      case acid
        when 'G'
          rna_strand << 'C'
        when 'C'
          rna_strand << 'G'
        when 'T'
          rna_strand << 'A'
        when 'A'
          rna_strand << 'U'
      end
    end
    return rna_strand.join

  end

  def convert_strand_to_dna(rna_strand)
    dna_strand = []

    rna_strand.split(//).each do |acid|
      case acid
        when 'C'
          dna_strand << 'G'
        when 'G'
          dna_strand << 'C'
        when 'A'
          dna_strand << 'T'
        when 'U'
          dna_strand << 'A'
      end
    end
    return dna_strand.join
  end

end
