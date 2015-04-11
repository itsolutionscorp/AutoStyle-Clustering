class Complement
  MAPPINGS = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  def self.of_dna(sequence)
    transcribe(sequence, MAPPINGS)
  end

  def self.of_rna(sequence)
    transcribe(sequence, MAPPINGS.invert)
  end

private

  def self.transcribe(sequence, mappings)
    sequence.each_char.map do |nucleotide|
      mappings[nucleotide]
    end.join
  end
end
