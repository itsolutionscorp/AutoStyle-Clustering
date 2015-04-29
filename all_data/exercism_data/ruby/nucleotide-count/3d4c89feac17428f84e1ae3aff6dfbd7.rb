class DNA
  
  def initialize(sequence)
    @sequence = sequence.chars 
    @dna_collection = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    @valid_nucleotides = %w(A T C G U)
    validate_dna(@sequence)
  end

  def count(nucleotide)
    @valid_nucleotides.include?(nucleotide) ?
      @sequence.count(nucleotide) : wrong_argument
  end

  def nucleotide_counts
    @sequence.each do |nucleotide|
      @dna_collection[nucleotide] += 1
    end
    @dna_collection
  end


  private

  def validate_dna(sequence)
    @sequence.each do |nucleotide|
      wrong_argument unless @dna_collection.keys.include?(nucleotide)
    end
  end

  def wrong_argument
    raise ArgumentError
  end
end
