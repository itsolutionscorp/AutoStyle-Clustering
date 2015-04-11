class DNA
  @@nucleotides = {'A' => true, 'G' => true, 'C' => true, 'T' => true, 'U' => true}  
  @@dna_symbols = ['A', 'G', 'C', 'T']

  def initialize dna_sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if dna_sequence.index(/[^#{@@dna_symbols.join}]/)

    @dna_sequence = dna_sequence    
  end

  def count nucleotide
    raise ArgumentError.new "Unrecognized Nucleotide: #{nucleotide}" unless @@nucleotides[nucleotide]
    nucleotide_counts[nucleotide] || 0
  end
  
  def nucleotide_counts
    @dna_count ||= dna_count
  end

  private
  def dna_count     
    @dna_sequence.chars.each_with_object(default_count) { |acid, counts| counts[acid] += 1 }
  end  

  def default_count
    @@dna_symbols.each_with_object({}) { |acid, counts| counts[acid] = 0}
  end
  
end
