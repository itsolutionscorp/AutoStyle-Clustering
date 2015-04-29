class Complement
  def self.of_dna(dna)
    rna = []
    dna.split("").each do |base|
      rna.push transcribe_dna(base)
    end
    rna.join("")
  end

  def self.of_rna(rna)
    dna = []
    rna.split("").each do |base|
      dna.push transcribe_rna(base)
    end
    dna.join("")
  end

  private

  def self.transcribe_dna(base)
    transcriptions = {:'A' => 'U', :'G' => 'C', :'C' => 'G', :'T' => 'A' }
    transcriptions[base.to_sym]
  end

  def self.transcribe_rna(base)
    transcriptions = {:'A' => 'T', :'G' => 'C', :'C' => 'G', :'U' => 'A' }
    transcriptions[base.to_sym]
  end
end
