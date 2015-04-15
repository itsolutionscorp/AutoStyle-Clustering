class Complement

  # create instance variable with relation of dna to respective
  # rna convert
  @dna_to_rna = {"a" => "u", "t" => "a", "c" => "g", "g" => "c"}

  # return a string of letters from dna by comparing to
  # letters in dna_to_rna and adding values
  def self.of_dna(dna)
    rnaArray = dna.chars.map { |char| @dna_to_rna[char.downcase].upcase }
    rnaArray.join()
  end

  # return a string of letters from rna by comparing to
  # letters in dna_to_rna and adding keys
  def self.of_rna(rna)
    dnaArray = rna.chars.map { |char| @dna_to_rna.key(char.downcase).upcase }
    dnaArray.join()
  end

end
