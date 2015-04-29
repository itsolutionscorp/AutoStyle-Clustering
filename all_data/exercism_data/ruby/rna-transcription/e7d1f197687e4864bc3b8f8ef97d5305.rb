module Complement
  DNA_TRANSFORM = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TRANSFORM = DNA_TRANSFORM.invert

  def self.of_rna(rna)
    raise ArgumentError if rna.include?('T')
    rna.chars.map{|c| RNA_TRANSFORM[c]}.join
  end
  def self.of_dna(dna)
    raise ArgumentError if dna.include?('U')
    dna.chars.map{|c| DNA_TRANSFORM[c]}.join
  end
end
