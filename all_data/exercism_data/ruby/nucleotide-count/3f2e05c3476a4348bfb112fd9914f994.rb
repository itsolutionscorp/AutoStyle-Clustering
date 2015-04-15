class DNA
  DNA_NUCLEOTIDES = ['A','T','C','G']
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + ['U']

  def initialize(dna_strand)
    @dna_strand = dna_strand
  end 

  def count(target_nucleotide)
    counts = nucleotide_counts
    if DNA_NUCLEOTIDES.include? target_nucleotide 
      counts[target_nucleotide]
    elsif ALL_NUCLEOTIDES.include? target_nucleotide 
      0
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    counts = initialize_counts
    @dna_strand.chars.each do |nucleotide|
        counts[nucleotide] += 1
    end
    counts
  end

  private
    def initialize_counts 
      DNA_NUCLEOTIDES.each_with_object({}) do |n, hash| 
        hash[n] = 0
      end
    end
end
