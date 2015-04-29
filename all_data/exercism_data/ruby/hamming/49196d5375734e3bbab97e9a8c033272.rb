class Hamming
  def self.compute dna_1, dna_2
    @dna_1 = dna_1.split(//)
    @dna_2 = dna_2.split(//)
    get_hamming_count
  end

  private
  
  def self.get_hamming_count
    max_strand_size = get_max 
    array_of_dna_pairs = make_pairs_array max_strand_size
    array_of_dna_pairs.inject(0) do |count, dna| 
      count += 1 if dna.first != dna.last 
      count
    end
  end
  
  def self.make_pairs_array(max_strand_size)
    strand_a = @dna_1[0,max_strand_size] 
    strand_b = @dna_2[0,max_strand_size]
    strand_a.zip(strand_b)
  end

  def self.get_max 
    if @dna_1.count < @dna_2.count 
      @dna_1.count 
    else 
      @dna_2.count
    end
  end
end
