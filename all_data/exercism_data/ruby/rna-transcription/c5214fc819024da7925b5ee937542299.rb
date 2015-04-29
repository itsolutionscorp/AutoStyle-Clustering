class Complement
  COMPLEMENTS = {
    'C' => 'G',
    'G' => 'C',
    'U' => 'A',
    'A' => 'T'
  }

  DNA_SEQ = "GCAT"
  RNA_SEQ = "CGUA"

  def self.of_dna input
    inverts = COMPLEMENTS.invert
    input.chars.map{ |c| inverts[c] }.join
  end

  def self.of_dna_tr input
    input.tr(DNA_SEQ, RNA_SEQ)
  end

  def self.of_rna input
    input.chars.map{ |c| COMPLEMENTS[c] }.join
  end

  def self.of_rna_tr input
    input.tr(RNA_SEQ, DNA_SEQ)
  end
end

require 'benchmark'

Benchmark.bmbm do |x|
  x.report("tr") { Complement.of_rna_tr('UGAACCCGACAUG') }
  x.report("map.join") { Complement.of_rna('UGAACCCGACAUG') }
end
