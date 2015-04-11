class Complement
  def self.of_dna(dna)
    dna.split('').map {|x| dna_to_rna[x]}.join
  end

  def self.of_rna(dna)
    dna.split('').map {|x| rna_to_dna[x]}.join
  end

  def self.dna_to_rna
    {'C'=>'G', 'G'=>'C', 'T'=>'A', 'A'=>'U'}
  end

  def self.rna_to_dna
    {'C'=>'G', 'G'=>'C', 'A'=>'T', 'U'=>'A'}
  end
end
