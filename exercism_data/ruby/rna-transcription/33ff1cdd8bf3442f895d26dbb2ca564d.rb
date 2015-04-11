class Complement
COMPLEMENTS = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(dna)
    result = []
    dna.chars.each do |char|
      COMPLEMENTS[char].nil? ? raise(ArgumentError) : result << COMPLEMENTS[char]
    end
    result.join
  end

  def self.of_rna(rna)
    result = []
    rna.chars.each do |char|
      COMPLEMENTS.key(char).nil?  ? raise(ArgumentError) : result << COMPLEMENTS.key(char)
    end
    result.join
  end

end
