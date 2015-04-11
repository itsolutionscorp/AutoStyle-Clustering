class Complement
  $hash_dna = {'G' => 'C',
			   'C' => 'G',
			   'T' => 'A',
	           'A' => 'U'}
  $hash_rna = {'G' => 'C',
			   'C' => 'G',
			   'U' => 'A',
	           'A' => 'T'}

  def self.of_dna(strand)
    strand.split("").map {|x| $hash_dna[x]}.join('')
  end
  
  def self.of_rna(strand)
  	strand.split("").map {|x| $hash_rna[x] }.join('')
  end
end
