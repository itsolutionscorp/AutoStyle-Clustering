class Complement
  @@dna_to_rna = { 
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  @@rna_to_dna = @@dna_to_rna.invert

  def self.transcribe(seq, invert = false)
    seq.split('').map do |i|
      unless invert
        @@dna_to_rna[i]
      else
        @@rna_to_dna[i]
      end
    end.join
  end

  def self.of_dna(seq)
    transcribe seq
  end

  def self.of_rna(seq)
    transcribe seq, true
  end
end
