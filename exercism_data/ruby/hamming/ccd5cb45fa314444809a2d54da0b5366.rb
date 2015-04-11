class DNA
  @@dna_symbols = ['A', 'G', 'C', 'T']
  @@nucleotides = @@dna_symbols | ['U']
  

  def initialize dna_sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? dna_sequence

    @dna_sequence = dna_sequence    
  end

  def hamming_distance other_dna
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? other_dna

    sequence_pairs(other_dna).reject {|pair| match?(pair) }.length
  end

  def count nucleotide
    raise ArgumentError.new "Unrecognized Nucleotide: #{nucleotide}" unless @@nucleotides.include? nucleotide
    
    @dna_sequence.count nucleotide
  end
  
  def nucleotide_counts
    @dna_count ||= dna_count
  end

  private
  def dna_count     
    @@dna_symbols.each_with_object({}) {|acid, counts| counts[acid] = count(acid) }    
  end  

  def illegal_characters? sequence
    sequence.index(/[^#{@@dna_symbols.join}]/)
  end

  def sequence_pairs other_dna
    other_dna.chars.zip(@dna_sequence.chars)
  end

  def match? pair
    pair[0] == pair[1] || pair.include?(nil)
  end

end
