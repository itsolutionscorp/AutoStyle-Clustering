class Complement
  DNA_TO_RNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna(sequence)
    transcribe(sequence, DNA_TO_RNA)
  end

  def self.of_rna(sequence)
    transcribe(sequence, RNA_TO_DNA)
  end

  def self.transcribe(sequence, mapping)
    sequence.chars.map { |item| mapping[item] }.join
  end
end
