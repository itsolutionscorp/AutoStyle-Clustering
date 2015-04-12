module Hamming
  extend self

  def compute(*strands)
    to_pairs = ->(a,b) { a.chars.zip(b.chars) }
    mutations = ->(nucleotides) { nucleotides.first != nucleotides.last }
    strands.reduce(&to_pairs).count(&mutations)
  end
end
