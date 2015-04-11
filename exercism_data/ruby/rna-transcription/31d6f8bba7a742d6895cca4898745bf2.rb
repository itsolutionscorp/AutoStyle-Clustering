class Complement
  DNA_TO_RNA = {
    'G'=>'C',
    'C'=>'G',
    'T'=>'A',
    'A'=>'U'  
  }

  def self.of_dna(dna)
    dna.chars.map{|x| DNA_TO_RNA[x]}.join
  end

  def self.of_rna(rna)
    rna.chars.map{|x| DNA_TO_RNA.invert[x]}.join
  end

end
