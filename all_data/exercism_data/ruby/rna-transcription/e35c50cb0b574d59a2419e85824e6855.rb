class Complement

  DNA_BASES = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA_BASES = DNA_BASES.invert

  def self.of_dna (strand)
    Complement.transcode strand, mapping: DNA_BASES
  end

  def self.of_rna (strand)
    Complement.transcode strand, mapping: RNA_BASES
  end

  def self.transcode (strand, mapping: {})
    complement = ""

    strand.each_char do |base|
      complement.concat(mapping[base])
    end

    complement
  end

end
