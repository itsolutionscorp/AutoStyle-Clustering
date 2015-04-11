class Complement
  def self.of_dna(dna)
    dna.chars.map { |n| {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}[n] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |n| {"G" => "C", "C" => "G", "U" => "A", "A" => "T"}[n] }.join
  end
end
