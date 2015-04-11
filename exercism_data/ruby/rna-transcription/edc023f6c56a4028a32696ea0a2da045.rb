class Complement
  @conversion_map = { 'G' => 'C',
                 'C' => 'G',
                 'T' => 'A',
                 'A' => 'U'
                }

  def self.of_dna(dna_strand)
    @rna_strand = String.new

    dna_strand.chars.each_with_index do |d|
      @rna_strand << @conversion_map[d]
    end
    @rna_strand
  end

  def self.of_rna(rna_strand)
    @dna_strand = String.new

    rna_strand.chars.each_with_index do |d|
      inverted = @conversion_map.invert
      @dna_strand << inverted[d]
    end
    @dna_strand
  end
end
