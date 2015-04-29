class Complement
  RNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.each_char.map do |char|
      RNA_MAP[char]
    end.join
  end

  def self.of_rna(rna)
    dna_map = Hash[RNA_MAP.to_a.map(&:reverse)]

    rna.each_char.map do |char|
      dna_map[char]
    end.join
  end
end
