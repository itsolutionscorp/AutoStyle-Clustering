class DNA
  @@dna_symbols = ['A', 'G', 'C', 'T']
  @@nucleotides = @@dna_symbols | ['U']
  

  def initialize sequence
    validate_dna sequence

    @sequence = sequence    
  end

  def hamming_distance other_sequence
    validate_dna other_sequence

    paired_nucleotides(other_sequence).select {|pair| mutation?(pair) }.length
  end

  def count nucleotide
    validate_nucleotide nucleotide
    
    @sequence.count nucleotide
  end
  
  def nucleotide_counts
    @dna_count ||= dna_count
  end

  private
  def dna_count     
    @@dna_symbols.each_with_object({}) {|acid, counts| counts[acid] = count(acid) }    
  end  

  def paired_nucleotides other_sequence
    other_sequence.chars.zip(@sequence.chars).take_while {|pair| !pair.include?(nil) }
  end

  def mutation? pair
    pair[0] != pair[1]
  end

  def validate_nucleotide nucleotide
    raise ArgumentError.new "Unrecognized Nucleotide: #{nucleotide}" unless @@nucleotides.include? nucleotide
  end

  def validate_dna sequence
    raise ArgumentError.new "Sequence contains non DNA symbols." if illegal_characters? sequence
  end

  def illegal_characters? sequence
    sequence.index(/[^#{@@dna_symbols.join}]/)
  end

end
