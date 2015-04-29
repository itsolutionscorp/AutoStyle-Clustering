class Complement
  MAP = {
    dna: { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' },
    rna: { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' },
  }

  def self.complement_of(type, acid)
    acid.chars.reduce('') { |res, n| res + MAP[type][n] }
  end

  def self.of_dna(dna)
    complement_of(:dna, dna)
  end

  def self.of_rna(rna)
    complement_of(:rna, rna)
  end
end
