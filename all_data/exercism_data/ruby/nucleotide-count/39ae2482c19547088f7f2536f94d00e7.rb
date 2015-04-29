class DNA
  def initialize(strand)
    raise ArgumentError unless valid_strand?(strand)
    @strand = strand
  end
  
  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @strand.chars.each_with_object(counter) do |n, counts|
      counts[n] += 1
    end
  end
  
  private
  
  def counter
    counter = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    counter.default = 0
    counter
  end

  def valid_strand?(strand)
    strand.chars.all? { |n| valid_nucleotide?(n) }
  end

  def valid_nucleotide?(n)
    ['A', 'T', 'C', 'G'].include?(n)
  end
end
