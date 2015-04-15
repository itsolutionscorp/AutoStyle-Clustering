class Complement
  @@transcription_table = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(sequence)
    sequence.gsub(/\w/, @@transcription_table)
  end

  def self.of_rna(sequence)
    sequence.gsub(/\w/, @@transcription_table.invert)
  end
end
