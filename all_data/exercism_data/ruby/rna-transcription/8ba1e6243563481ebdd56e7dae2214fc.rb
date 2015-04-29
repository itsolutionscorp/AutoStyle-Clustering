class Complement
  DNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna)
    dna.chars.map do |c|
      DNA_MAP[c] || raise(ArgumentError)
    end.join
  end

  def self.of_rna(rna)
    rna.chars.map do |c|
      DNA_MAP.key(c) || raise(ArgumentError)
    end.join
  end
end
