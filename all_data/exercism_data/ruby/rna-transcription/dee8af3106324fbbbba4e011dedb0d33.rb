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
#   def slow_to_rna
#     @sequence.gsub(THYMINE, URACIL)
#   end
# end
#
# sequences = (0...1_000_000).map do
#   DNA.new((0...20).map { [DNA::ADENINE, DNA::CYTOSINE, DNA::GUANINE, DNA::THYMINE].sample }.join)
# end
#
# Benchmark.bm do |x|
#   x.report { sequences.each { |sequence| sequence.slow_to_rna } }
#   x.report { sequences.each { |sequence| sequence.to_rna } }
# end
#
#       user     system      total        real
#   5.500000   0.150000   5.650000 (  5.637118)
#   0.550000   0.000000   0.550000 (  0.551840)
