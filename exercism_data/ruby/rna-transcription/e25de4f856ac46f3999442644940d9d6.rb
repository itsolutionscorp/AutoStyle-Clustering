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
    of_dna_tr input
  end

  def self.of_rna input
    of_rna_tr input
  end

  def self.of_dna_map input
    inverts = COMPLEMENTS.invert
    input.chars.map{ |c| inverts[c] }.join
  end

  def self.of_dna_tr input
    input.tr(DNA_SEQ, RNA_SEQ)
  end

  def self.of_rna_map input
    input.chars.map{ |c| COMPLEMENTS[c] }.join
  end

  def self.of_rna_tr input
    input.tr(RNA_SEQ, DNA_SEQ)
  end
end

require 'benchmark'

lots_of = 10000
Benchmark.bmbm do |x|
  x.report("tr") { lots_of.times { Complement.of_rna_tr('UGAACCCGACAUG') } }
  x.report("map.join") { lots_of.times { Complement.of_rna_map('UGAACCCGACAUG') } }
end
