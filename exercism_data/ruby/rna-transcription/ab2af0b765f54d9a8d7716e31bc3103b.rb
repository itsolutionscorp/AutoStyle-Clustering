class Complement
  DNA_MAP = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(dna_string)
    dna_string.chars.map do |c|
      DNA_MAP[c] || raise(ArgumentError, 'A DNA string cannot include uracil.')
    end.join
  end

  def self.of_rna(rna_string)
    rna_string.chars.map do |c|
      DNA_MAP.key(c) || raise(ArgumentError, 'An RNA string can not include thymine.')
    end.join
  end
end
