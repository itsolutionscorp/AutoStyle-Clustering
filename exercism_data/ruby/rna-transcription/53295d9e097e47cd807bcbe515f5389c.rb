class Complement
  TRANSCRIPTION = {
                    'G' => 'C',
                    'C' => 'G',
                    'T' => 'A',
                    'A' => 'U'
                  }
  
  def self.of_dna(dna)
    dna.split('').map { |n| TRANSCRIPTION[n] }.join
  end

  def self.of_rna(rna)
    rna.split('').map { |n| TRANSCRIPTION.key(n) }.join
  end
end
