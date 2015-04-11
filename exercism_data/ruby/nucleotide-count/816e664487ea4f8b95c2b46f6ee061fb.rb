class DNA
  attr_reader :nucleotides

  def initialize(nucleotides)
    @nucleotides = nucleotides.split("")
    check_for_dna_only
  end

  def count(nucleotide_type)
    if all_nucleotides.member?(nucleotide_type)
      nucleotides.count(nucleotide_type)
    else
      dna_error
    end
  end

  def nucleotide_counts
    dna_count = {"A" => 0, "T" => 0, "C" => 0, "G" => 0}
    dna_count.merge!(dna_count) { |key, v1| count(key) }
  end

  private

    def check_for_dna_only
      @nucleotides.each do |nucleotide|
        dna_error unless dna_nucleotides.member? nucleotide
      end
    end

    def dna_error
      raise ArgumentError, "DNA nucleotides only"
    end

    def all_nucleotides
      %w( A C G T U )
    end

    def dna_nucleotides
      %w( A C G T )
    end
end
