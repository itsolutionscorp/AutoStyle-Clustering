class Complement
   
   @TRANSCRIPTION = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  
  def self.of_dna(strand)
  	strand.chars.map { |char| @TRANSCRIPTION[char] }.join
  end

  def self.of_rna(strand)
  	strand.chars.map { |char| @TRANSCRIPTION.key(char) }.join
  end

end
