TRANSCRIPTION = {
  'G' => 'C',
  'C' => 'G',
  'T' => 'A',
  'A' => 'U'
}

class Complement
  def self.of_dna(strand)
    rna = ''
    strand.each_char do |x|
      rna << TRANSCRIPTION[x]
    end
    rna
  end

  def self.of_rna(strand)
    dna = ''
    strand.each_char do |x|
      dna << TRANSCRIPTION.key(x)
    end
    dna
  end
end
