class DNA
  class << self
    def all_nucleotides
      %w{A C G T U}
    end

    def dna_nucleotides
      all_nucleotides - ['U']
    end
  end

  def initialize(chain)
    raise ArgumentError unless valid_dna_chain?(chain)
    @chain = chain
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @counts ||= count_nucleotides
  end

  private

  def count_nucleotides
    nucleotides(@chain).each_with_object(dna_count_hash) { |n, hash| hash[n] += 1 }
  end

  def valid_dna_chain?(chain)
    nucleotides(chain).all? { |n| DNA.dna_nucleotides.include? n }
  end

  def valid_nucleotide?(nucleotide)
    DNA.all_nucleotides.include? nucleotide
  end

  def nucleotides(chain)
    chain.chars
  end

  def dna_count_hash
    DNA.dna_nucleotides.each_with_object(Hash.new(0)) { |n, hash| hash[n] = 0 }
  end

end
