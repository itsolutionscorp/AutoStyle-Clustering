module Hamming
  extend self

  def compute(*strands)
    to_arrays = ->(a,b) { a.chars.zip(b.chars) }
    mutations = ->(points) { points.all? and not points.uniq.one? }
    strands.reduce(&to_arrays).count(&mutations)
  end
end
