class Complement

  DNA = 'ATCG'
  RNA = 'UAGC'

  def self.of_dna(sequence)
    sequence.tr(DNA, RNA)
  end

  def self.of_rna(sequence)
    sequence.tr(RNA, DNA)
  end
end
