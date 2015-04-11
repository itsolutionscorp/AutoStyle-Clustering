class Complement

	RNA_COMPLEMENT = {'G'=>'C','C'=>'G','U'=>'A', 'A'=>'T'}
  DNA_COMPLEMENT = {'G'=>'C','C'=>'G','T'=>'A', 'A'=>'U'}

	def self.of_dna(sequence)
		complement = ''
		sequence.split('').each {|x| complement << DNA_COMPLEMENT[x]}
		complement
	end

	def self.of_rna(sequence)
		complement = ''
		sequence.split('').each {|x| complement << RNA_COMPLEMENT[x]}
		complement
  end

end
