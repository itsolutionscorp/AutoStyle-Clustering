class Complement
  @table = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}
  def self.of_dna(dna)
    raise ArgumentError if dna['U']
    dna.gsub(/./, @table)
  end

  def self.of_rna(rna)
    raise ArgumentError if rna['T']
    rna.gsub(/./, @table.invert)
  end
end
