class Complement

  def self.of_dna(stra)
    code = {'G'=>'C','C'=>'G','T'=>'A','A'=>'U'}
    stra.chars.map {|c| code[c] }.join('') 
  end
  
  def self.of_rna(stra)
    code = {'C'=>'G','G'=>'C','U'=>'A','A'=>'T'}
    stra.chars.map {|c| code[c] }.join('')
  end
  
end
