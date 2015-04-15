class Complement
  def self.of_dna(dna_sequence)
    rna_sequence = String.new
	dna_sequence.chars do|dna_letter|
	  rna_letter = case dna_letter
	    when 'C' then 'G'
		when 'G' then 'C'
		when 'T' then 'A'
		when 'A' then 'U'
		else
		  'Error'
	  end
	  rna_sequence += rna_letter
	end
	return rna_sequence
  end
  def self.of_rna(rna_sequence)
    dna_sequence = String.new
	rna_sequence.chars do|rna_letter|
	  dna_letter = case rna_letter
	    when 'C' then 'G'
		when 'G' then 'C'
		when 'U' then 'A'
		when 'A' then 'T'
		else
		  'Error'
	  end
	  dna_sequence += dna_letter
	end
	return dna_sequence
  end
end
