class Complement
  def self.of_dna(dna)
    self.complement(dna)
  end

  def self.of_rna(rna)
    self.complement(rna).gsub(/U/, "T")
  end

  private

  def self.complement(string)
    string.each_char.map do |c|
      { "G" => "C", "C" => "G", "T" => "A", "A" => "U", "U" => "A" }[c]
    end.join
  end
end
