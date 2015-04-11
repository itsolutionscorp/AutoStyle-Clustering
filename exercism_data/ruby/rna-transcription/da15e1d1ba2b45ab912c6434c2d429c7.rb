class Complement
  DNA_COMPLEMENT_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_COMPLEMENT_MAP = DNA_COMPLEMENT_MAP.invert

  def self.of_dna(dna)
    dna.each_char.map { |x| DNA_COMPLEMENT_MAP[x] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |x| RNA_COMPLEMENT_MAP[x] }.join
  end
end
