class Complement

  DNA_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  def self.of_dna(dna)
    dna.chars.each_with_index do |char, index|
      dna[index] = DNA_RNA[char]
    end
  end

  def self.of_rna(rna)
    rna.chars.each_with_index do |char, index|
      rna[index] = DNA_RNA.key(char)
    end
  end

end
