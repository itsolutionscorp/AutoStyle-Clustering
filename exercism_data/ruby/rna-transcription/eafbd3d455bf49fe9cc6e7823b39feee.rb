module Complement
  def self.of_dna seq
    seq.tr 'ACGT', 'UGCA'
  end

  def self.of_rna seq
    seq.tr 'UGCA', 'ACGT'
  end
end
