class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U',
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    self.map_chars(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    self.map_chars(rna, RNA_TO_DNA)
  end

private

  def self.map_chars(str, mapping)
    mapped = ''
    str.chars.each do |char|
      mapped += mapping[char]
    end
    return mapped
  end
end
