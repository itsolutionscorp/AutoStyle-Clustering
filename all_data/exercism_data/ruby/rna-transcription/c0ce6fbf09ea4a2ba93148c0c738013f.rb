DNA = "ACGT"
RNA = "UGCA"
class Complement

  def self.of_rna(rna_strand)
    rna_strand.tr(RNA, DNA)
  end


  def self.of_dna(dna_strand)
    dna_strand.tr(DNA, RNA)
  end

end
