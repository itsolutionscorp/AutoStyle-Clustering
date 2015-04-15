class Complement
  @@complement = {
    'C'=>'G',
    'G'=>'C',
    'T'=>'A',
    'A'=>'U'
  }
  def self.of_dna(dna)
    dna.chars.map {|item| @@complement[item]}.join ''
  end
  def self.of_rna(rna)
    rna.chars.map {|item| @@complement.key(item)}.join ''
  end
end
