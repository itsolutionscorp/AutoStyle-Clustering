class Complement
  TRANSCRIPTION = { 'G' => 'C', 
                    'C' => 'G', 
                    'T' => 'A', 
                    'A' => 'U'}
  def self.of_dna(strand)
    strand.scan(/[A-Z]/).map{|nucleotide| TRANSCRIPTION[nucleotide] }.join
  end

  def self.of_rna(strand)
    strand.scan(/[A-Z]/).map{|nucleotide| TRANSCRIPTION.key(nucleotide) }.join
  end
end
