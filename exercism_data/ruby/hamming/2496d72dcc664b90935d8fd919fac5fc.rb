require 'pry'

class Hamming

  def self.compute(gene_1, gene_2)
    strand_1 = gene_1.split(//)
    strand_2 = gene_2.split(//)

    short_strand = strand_1.length < strand_2.length ? strand_1 : strand_2
    long_strand  = strand_2.length < strand_1.length ? strand_2 : strand_1

    short_strand.zip(long_strand).inject(0) { |memo, tuple| memo + (tuple.first != tuple.last ? 1 : 0) }
  end
end
