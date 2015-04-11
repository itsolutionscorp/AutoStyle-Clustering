class Complement
  @dna_rna = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  @rna_dna = @dna_rna.invert
  def self.of_dna key
    rna_str = ""

    key.each_char do |char|
      rna_str = rna_str + @dna_rna[char]
    end

    rna_str
  end

  def self.of_rna key
    dna_str = ""

    key.each_char do |char|
      dna_str = dna_str + @rna_dna[char]
    end

    dna_str
  end

end
