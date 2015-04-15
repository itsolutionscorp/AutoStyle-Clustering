class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(dna)
    replace_chars(dna, DNA_TO_RNA)
  end

  def self.of_rna(rna)
    replace_chars(rna, RNA_TO_DNA)
  end

  private

  # Each character in the given string is replaced according to
  # the given dictionary hash (or deleted if not in the dictionary).
  def self.replace_chars(string, dictionary)
    string.gsub(/./, dictionary)
  end
end
