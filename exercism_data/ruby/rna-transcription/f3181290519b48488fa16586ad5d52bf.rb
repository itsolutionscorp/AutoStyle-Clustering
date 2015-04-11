class Complement
  def self.of_dna(strand)
    transcription = {
      'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U'
    }
    strand.chars.map { |n| transcription[n] }.join
  end

  def self.of_rna(strand)
    transcription = {
      'C' => 'G',
      'G' => 'C',
      'A' => 'T',
      'U' => 'A'
    }
    strand.chars.map { |n| transcription[n] }.join
  end
end
