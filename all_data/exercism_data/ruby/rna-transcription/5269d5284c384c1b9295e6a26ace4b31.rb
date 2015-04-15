class Complement
  RNA = {"G"=>"C", "C"=>"G", "T"=>"A", "A"=>"U"}

  def self.of_dna(dna)
    dna.chars.map { |letter| RNA[letter] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |letter| RNA.key(letter) }.join
  end
end
