class Complement

  DNA_TO_RNA = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }
  RNA_TO_DNA = DNA_TO_RNA.invert

  def self.of_dna (strand)
    Complement.transcode strand, mapping: DNA_TO_RNA
  end

  def self.of_rna (strand)
    Complement.transcode strand, mapping: RNA_TO_DNA
  end

  def self.transcode (strand, mapping: {})
    complement = ""

    strand.each_char do |base|
      complement.concat(mapping[base])
    end

    complement
  end

end
