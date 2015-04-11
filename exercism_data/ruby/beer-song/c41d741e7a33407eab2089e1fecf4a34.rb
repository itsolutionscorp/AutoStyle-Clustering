class DNA
  def initialize(sequence)
    @sequence    = sequence
    @counts_memo = {}
  end

  def count(char)
    raise ArgumentError unless nucleotides.include?(char)
    @counts_memo[char] ||= @sequence.count(char)
  end

  def nucleotide_counts
    dna_nucleotides.map{ |n| { n => count(n) } }.reduce(&:merge!)
  end

  private
  def nucleotides
    %w[A C T G U]
  end

  def dna_nucleotides
    nucleotides - ['U']
  end
end
