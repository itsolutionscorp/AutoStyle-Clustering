class Complement

  # create instance variable with relation of dna to respective
  # rna convert
  @dna_to_rna = {"A" => "U", "T" => "A", "C" => "G", "G" => "C"}

  # return a string of letters from dna by comparing to
  # letters in dna_to_rna and adding values
  def self.of_dna(dna)
    rnaArray = dna.chars.map { |char| @dna_to_rna[char] }
    rnaArray.join()
  end

  # return a string of letters from rna by comparing to
  # letters in dna_to_rna and adding keys
  def self.of_rna(rna)
    dnaArray = rna.chars.map { |char| @dna_to_rna.key(char) }
    dnaArray.join()
  end

end
