class DNA
  def initialize(strand)
    @strand = strand
  end
  
  def count(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @strand.chars.each_with_object(counter) do |n, counts|
      counts[n] += 1 if valid_nucleotide?(n)
    end
  end
  
  private
  
  def counter
    counter = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    counter.default = 0
    counter
  end

  def valid_nucleotide?(n)
    ['A', 'T', 'C', 'G'].include?(n)
  end
end
