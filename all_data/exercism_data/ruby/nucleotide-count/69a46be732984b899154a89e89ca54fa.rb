require "set"

class Nucleotide
  DNA_BASES = %w{A C G T}
  RNA_BASES = %w{A C G U}

  ALL_BASES = DNA_BASES | RNA_BASES

  def self.valid_base?(base)
    ALL_BASES.include?(base)
  end
end

class DNA
  def initialize(coding_strand)
    @coding_strand = coding_strand.upcase
  end

  def nucleotide_counts
    @nucleotide_counts ||= tally_dna_nucleotides
  end

  def count(nucleotide)
    raise ArgumentError unless Nucleotide.valid_base?(nucleotide)
    
    nucleotide_counts.fetch(nucleotide, 0)
  end

  private
    def tally_dna_nucleotides
      Nucleotide::DNA_BASES.each_with_object({}) do |nucleotide, counts|
        counts[nucleotide] = @coding_strand.count(nucleotide)
      end
    end
end
