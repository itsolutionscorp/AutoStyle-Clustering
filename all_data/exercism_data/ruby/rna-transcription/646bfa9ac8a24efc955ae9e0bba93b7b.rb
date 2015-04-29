class Complement

  RNA_COMPLEMENT = {'G'=>'C','C'=>'G','U'=>'A', 'A'=>'T'}
  DNA_COMPLEMENT = {'G'=>'C','C'=>'G','T'=>'A', 'A'=>'U'}

  def self.of_dna(sequence)
    result = ""
    sequence.each_char { |c| result += DNA_COMPLEMENT[c]}
    return result
  end

  def self.of_rna(sequence)
    result = ""
    sequence.each_char { |c| result += RNA_COMPLEMENT[c]}
    return result
  end

end
