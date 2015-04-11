class DNA  
  def initialize(dna)
    throw ArgumentError.new unless valid_dna?(dna)
    @dna = dna
  end
  
  def count(nucleotide)
    throw ArgumentError.new unless valid_nucleotide?(nucleotide)
    @dna.count(nucleotide)
  end
  
  def nucleotide_counts
    VALID_DNA.chars.each_with_object(Hash.new) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end
  
  private
  
  VALID_DNA = 'ATCG'
  VALID_NUCLEOTIDE = VALID_DNA + 'U'

  def valid_dna?(dna)
    dna.chars.all? { |c| VALID_DNA.include?(c) }
  end

  def valid_nucleotide?(nucleotide)
    VALID_NUCLEOTIDE.include?(nucleotide)
  end
end
