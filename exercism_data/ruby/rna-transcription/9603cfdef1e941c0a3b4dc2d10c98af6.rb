class Complement
  @transcription_key = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(strand)
    complement(strand, @transcription_key)
  end

  def self.of_rna(strand)
    complement(strand, @transcription_key.invert)
  end

  def self.complement(strand, key)
    strand.chars.map {|residue| key[residue]}.join
  end
end
