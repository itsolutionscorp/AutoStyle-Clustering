class Nucleotide
  class << self
    alias :from_dna :new
  end

  attr_reader :histogram

  def initialize(dna)
    @histogram = %w(A T C G).map { |n| [n,0] }.to_h
    @histogram.default_proc = -> (_,_) { fail ArgumentError }
    dna.chars.each { |n| @histogram[n] += 1 }
  end
  
  def count(x)
    histogram[x]
  end
end
