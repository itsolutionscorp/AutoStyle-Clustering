class Complement
  DNA = "GCTA"
  RNA = "CGAU"

  def self.of_dna(dna_strand)
    if dna_strand.tr(DNA, RNA) == dna_strand
      raise(ArgumentError)
      else dna_strand.tr(DNA, RNA)
    end
    end

  def self.of_rna(rna_strand)
    if rna_strand.tr(RNA, DNA) == rna_strand
      raise(ArgumentError)
      else
    rna_strand.tr(RNA, DNA)
    end
  end
end
