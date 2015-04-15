class Complement
    TRANSCRIPTION = {'A'=>'U', 'T'=>'A', 'C'=>'G', 'G'=>'C' }
  def self.of_dna string
    string.split(//).map{|i| TRANSCRIPTION[i] }.join
  end
  def self.of_rna string
    rna_trans = TRANSCRIPTION.invert
    string.split(//).map{|i| rna_trans[i] }.join
  end
end
