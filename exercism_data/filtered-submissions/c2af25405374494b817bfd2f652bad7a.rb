module Hamming
  extend self

  def compute(*strands)
    to_pairs = ->(a,b) { a.chars.zip(b.chars) }
    mutations = ->(nucleotides) { !nucleotides.uniq.one? }
    strands.reduce(&to_pairs).count(&mutations)
  end
end
