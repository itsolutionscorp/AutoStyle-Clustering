class Complement
  TO_RNA = {C:'G',G:'C',T:'A',A:'U'}
  TO_DNA = {C:'G',G:'C',U:'A',A:'T'}

  def self.of_dna string
    transcribe string, TO_RNA
  end

  def self.of_rna string
    transcribe string, TO_DNA
  end

  def self.transcribe string, transcription
    string.each_char.map { |c| transcription[c.to_sym] }.join
  end
end
