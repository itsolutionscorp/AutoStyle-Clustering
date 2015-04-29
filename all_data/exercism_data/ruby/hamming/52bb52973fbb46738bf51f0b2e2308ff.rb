class DNA
  @@dna_symbols = ['A', 'G', 'C', 'T']
  @@nucleotides = @@dna_symbols | ['U']
  

  def initialize sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? sequence

    @sequence = sequence    
  end

  def hamming_distance other_sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? other_sequence

    paired_nucleotides(other_sequence).select {|pair| mmutation?(pair) }.length
  end

  def count nucleotide
    raise ArgumentError.new "Unrecognized Nucleotide: #{nucleotide}" unless @@nucleotides.include? nucleotide
    
    @sequence.count nucleotide
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

  def paired_nucleotides other_sequence
    other_sequence.chars.zip(@sequence.chars).take_while {|pair| !pair.include?(nil) }
  end

  def mmutation? pair
    pair[0] != pair[1]
  end

end
