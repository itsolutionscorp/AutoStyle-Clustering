class Complement

  @rna_conversaion_hash = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  @dna_conversion_hash = {'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A'}

  def self.of_dna(dna)
    dna_array = dna.split('')
    @rna_strand_array = []
    dna_array.each do |nucleotide|
      @rna_conversaion_hash.each do |k,v|
        if nucleotide == k
          @rna_strand_array.push(v)
        end
      end
    end
    @rna_strand_array.join
  end

  def self.of_rna(rna)
    rna_array = rna.split('')
    @dna_strand_array = []
    rna_array.each do |nucleotide|
      @dna_conversion_hash.each do |k,v|
        if nucleotide == k
          @dna_strand_array.push(v)
        end
      end
    end
    @dna_strand_array.join
  end

end
