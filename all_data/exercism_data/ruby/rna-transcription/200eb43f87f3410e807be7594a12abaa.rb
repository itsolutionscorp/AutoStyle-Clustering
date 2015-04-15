class Complement
  def self.of_dna(dna)
    rna = ""
    complements = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

    dna.chars.to_a.each do |letter|
      rna += complements[letter]
    end

    rna
  end

  def self.of_rna(rna)
    dna = ""
    complements = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }

    rna.chars.to_a.each do |letter|
      dna += complements[letter]
    end

    dna
  end
end
