class Complement

  DNA_COMPLEMENT = { 'G' => 'C',
                     'C' => 'G',
                     'T' => 'A',
                     'A' => 'U' }
  RNA_COMPLEMENT = DNA_COMPLEMENT.invert

  def self.of_dna(dna_strand)
    new(dna_strand, :to_rna).convert
  end

  def self.of_rna(rna_strand)
    new(rna_strand, :to_dna).convert
  end

  def initialize(genetic_strand, conversion_method)
    @genetic_strand = genetic_strand
    @conversion_method = conversion_method
  end

  def convert
    converted = @genetic_strand.chars.map do |genetic_char|
      send(@conversion_method, genetic_char)
    end
    converted.join
  end

  private

  def to_dna(rna)
    RNA_COMPLEMENT[rna]
  end

  def to_rna(dna)
    DNA_COMPLEMENT[dna]
  end
end
