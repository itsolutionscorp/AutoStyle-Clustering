class DNA
  @@nucleotides = ['A', 'G', 'C', 'T', 'U']
  @@dna_symbols = ['A', 'G', 'C', 'T']

  def initialize dna_sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? dna_sequence

    @dna_sequence = dna_sequence    
  end

  def count nucleotide
    raise ArgumentError.new "Unrecognized Nucleotide: #{nucleotide}" unless @@nucleotides.include? nucleotide
    
    @dna_sequence.count(nucleotide)
  end
  
  def nucleotide_counts
    @dna_count ||= dna_count
  end

  private
  def dna_count     
    @@dna_symbols.each_with_object({}) {|acid, counts| counts[acid] = count acid }    
  end  

  def illegal_characters? sequence
    sequence.index(/[^#{@@dna_symbols.join}]/)
  end
  
end
