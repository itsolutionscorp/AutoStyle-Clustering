class Complement
  @@transcription_table = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(sequence)
    self.translate(sequence, @@transcription_table)
  end

  def self.of_rna(sequence)
    self.translate(sequence, @@transcription_table.invert)
  end

  private
  def self.translate(sequence, table)
    new_sequence = []
    sequence.split('').each_with_index do |char, index|
      new_sequence << table[char]
    end
    new_sequence.join('')
  end
end
