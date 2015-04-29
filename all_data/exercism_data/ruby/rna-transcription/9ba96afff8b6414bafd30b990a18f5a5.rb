module Complement
  module_function
  DNA = 'GCTA'
  RNA = 'CGAU'

  def of_dna(dna)
    raise ArgumentError unless dna =~ %r{^[#{DNA}]*$}
    dna.tr DNA, RNA
  end

  def of_rna(rna)
    raise ArgumentError unless rna =~ %r{^[#{RNA}]*$}
    rna.tr RNA, DNA
  end
end
