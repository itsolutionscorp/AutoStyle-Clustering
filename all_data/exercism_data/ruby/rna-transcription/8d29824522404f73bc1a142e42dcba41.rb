class Complement
  # hash associating values for translation
  @@dictionary = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  
  # translate DNA to RNA
  def self.of_dna(dna)
    rna = ''
    dna.split(//).each_index { |i| rna[i] = @@dictionary[dna[i]] }
    rna
  end
  
  # translate RNA to DNA
  def self.of_rna(rna)
    dna = ''
    rna.split(//).each_index { |i| dna[i] = @@dictionary.key(rna[i]) }
    dna
  end
end
