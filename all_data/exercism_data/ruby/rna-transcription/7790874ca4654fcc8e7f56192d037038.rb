class Complement
  DNA = {
    'C' => 'G',
    'G' => 'C',
    'T' => 'A',
    'A' => 'U',
  }

  RNA = DNA.invert

  def self.of_dna(dna)
    dna.chars.map do |char|
      DNA[char]
    end.join
  end

  def self.of_rna(rna)
    rna.chars.map do |char|
      RNA[char]
    end.join
  end

end
