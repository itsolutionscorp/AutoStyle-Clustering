class Complement

  DNA_BASES = "GCTA"
  RNA_BASES = "CGAU"

  def self.of_dna (nucleotide_bases)
    transcode nucleotide_bases, from: DNA_BASES, to: RNA_BASES
  end

  def self.of_rna (nucleotide_bases)
    transcode nucleotide_bases, from: RNA_BASES, to: DNA_BASES
  end

  def self.transcode (nucleotide_bases, from: "", to: "")
    complement = ""

    nucleotide_bases.each_char do |base|
      base_index = from.index(base)
      complement.concat(to[base_index])
    end

    complement
  end

end
