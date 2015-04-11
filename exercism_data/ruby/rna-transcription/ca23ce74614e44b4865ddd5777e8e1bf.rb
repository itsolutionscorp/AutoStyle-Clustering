class Complement
  @@dna_to_rna = { 
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  @@rna_to_dna = @@dna_to_rna.invert

  def self.transcribe(seq, seq_map)
    seq.split('').map do |i|
      seq_map[i]
    end.join
  end

  def self.of_dna(seq)
    transcribe seq, @@dna_to_rna
  end

  def self.of_rna(seq)
    transcribe seq, @@rna_to_dna
  end
end
