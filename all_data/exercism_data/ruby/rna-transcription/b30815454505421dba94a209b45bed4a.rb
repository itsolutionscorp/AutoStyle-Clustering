class Complement
  
  def transcribe(old_sequence,pairs)
    new_sequence = String.new
	old_sequence.chars do|old_letter|
	  pairs.each do|pair|
	    if old_letter == pair[0] then new_sequence += pair[1] end
	  end
	end
	return new_sequence
  end
  
  def self.of_dna(dna_sequence)
    pairs = [['C','G'],['G','C'],['T','A'],['A','U']]
	return Complement.new.transcribe(dna_sequence,pairs)
  end
 
 def self.of_rna(rna_sequence)
    pairs = [['G','C'],['C','G'],['A','T'],['U','A']]
	return Complement.new.transcribe(rna_sequence,pairs)
  end

  end
