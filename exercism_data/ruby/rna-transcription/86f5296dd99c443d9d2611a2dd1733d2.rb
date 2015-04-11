class Complement

  DNA = 'GCTA'
  RNA = 'CGAU'

  def self.of_dna(dna)
    raise ArgumentError if (DNA.split('') & dna.split('')).empty?
    dna.tr(DNA, RNA)
  end

  def self.of_rna(rna)
    raise ArgumentError if (RNA.split('') & rna.split('')).empty?
    rna.tr(RNA, DNA)
  end
end
