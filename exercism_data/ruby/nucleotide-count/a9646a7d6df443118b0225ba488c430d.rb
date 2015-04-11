class DNA
  attr_reader :nucleotides

  def initialize(nucleotides)
    self.nucleotides = nucleotides
  end

  def count(nucleotide_type)
    dna_error unless all_nucleotides.member? nucleotide_type
    counter = nucleotides.select {|type| type == nucleotide_type}
    counter.length
  end

  def nucleotide_counts
    dna_count = {"A" => 0, "T" => 0, "C" => 0, "G" => 0}
    dna_count.merge!(dna_count) { |key, v1| count(key) }
  end

  private

    def nucleotides=(new_nucleotides)
      new_nucleotides = new_nucleotides.chars
      new_nucleotides.each do |nucleotide|
        dna_error unless dna_nucleotides.member? nucleotide
      end
      @nucleotides = new_nucleotides
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
