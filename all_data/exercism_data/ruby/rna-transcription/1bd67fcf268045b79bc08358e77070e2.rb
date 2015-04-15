class Complement

  def self.compdna(ch)
    comp = {"G" => "C", "C" => "G", "T" => "A", "A" => "U" }
    comp[ch]
  end

  def self.comprna(ch)
    comp = {"G" => "C", "C" => "G", "U" => "A", "A" => "T"}
    comp[ch]
  end

  def self.of_rna(rna)
    rna.chars.map { |ch| Complement.comprna(ch) }.join
  end

  def self.of_dna(dna)
    dna.chars.map {|ch| Complement.compdna(ch) }.join
  end

end
