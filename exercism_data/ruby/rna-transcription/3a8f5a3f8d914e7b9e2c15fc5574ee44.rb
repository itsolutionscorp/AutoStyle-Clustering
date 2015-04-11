module Complement
  DNA = 'GCTA'
  RNA = 'CGAU'

  def self.of_dna(sequence)
    raise ArgumentError if !sequence.index(/[#{DNA}]/)
    sequence.tr(DNA, RNA)
  end

  def self.of_rna(sequence)
    raise ArgumentError if !sequence.index(/[#{RNA}]/)
    sequence.tr(RNA, DNA)
  end
end
