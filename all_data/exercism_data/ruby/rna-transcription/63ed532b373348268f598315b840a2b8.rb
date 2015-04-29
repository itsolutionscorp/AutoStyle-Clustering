class Complement

  def self.of_dna(strand)
    strand_transition(false, strand)
  end

  def self.of_rna(strand)
    strand_transition(true, strand)
  end

  # Transfer chars according to the transcript dependend on direction
  def self.strand_transition(inverse, strand)
    strand.split(//).each_with_index do |strand_el, index|
      transcript = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
      transcript = transcript.invert if inverse 
      strand[index] = transcript[strand_el]
    end
    strand
  end

end
