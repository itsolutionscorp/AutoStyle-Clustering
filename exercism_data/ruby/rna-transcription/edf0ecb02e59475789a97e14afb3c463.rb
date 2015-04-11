class Complement
  RNA_COMPLEMENTS = {'C'=>'G', 'G'=>'C', 'T'=>'A', 'A'=>'U'}

  DNA_COMPLEMENTS = {'C'=>'G', 'G'=>'C', 'U'=>'A', 'A'=>'T'}

  def self.of_dna(rna)
    rnas = rna.chars
    rnas.collect{ |rna| RNA_COMPLEMENTS[rna] }.join
  end

  def self.of_rna(dna)
    dnas = dna.chars
    dnas.collect{ |dna| DNA_COMPLEMENTS[dna] }.join
  end

end
