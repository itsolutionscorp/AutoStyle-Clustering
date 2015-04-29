class Complement

  def self.of_dna(x)
    x.split(//).map { |y| COMPLEMENTS[y] }.reduce(:+)
  end

  def self.of_rna(x)
    x.split(//).map { |y| COMPLEMENTS.key(y) }.reduce(:+)
  end

  private

  COMPLEMENTS = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

end
