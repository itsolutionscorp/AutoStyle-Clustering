class DNA
  ADENINE  = 'A'
  CYTOSINE = 'C'
  GUANINE  = 'G'
  THYMINE  = 'T'
  URACIL   = 'U'

  def initialize(sequence)
    @sequence = sequence.upcase
  end

  def to_rna
    @sequence.tr(THYMINE, URACIL)
  end
end

# require 'benchmark'
#
# class DNA
#   def to_rna_gsub_string
#     @sequence.gsub(THYMINE, URACIL)
#   end
#
#   def to_rna_gsub_regex
#     @sequence.gsub(/T/i, 'U')
#   end
# end
#
# sequences = (0...1_000_000).map do
#   DNA.new((0...20).map { [DNA::ADENINE, DNA::CYTOSINE, DNA::GUANINE, DNA::THYMINE].sample }.join)
# end
#
# Benchmark.bm do |x|
#   x.report { sequences.each { |sequence| sequence.to_rna_gsub_string } }
#   x.report { sequences.each { |sequence| sequence.to_rna_gsub_regex } }
#   x.report { sequences.each { |sequence| sequence.to_rna } }
# end
#
#       user     system      total        real
#   5.550000   0.170000   5.720000 (  5.727600)
#   5.070000   0.120000   5.190000 (  5.174385)
#   0.530000   0.000000   0.530000 (  0.537263)
