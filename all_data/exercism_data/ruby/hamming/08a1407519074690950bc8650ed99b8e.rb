module Hamming
  extend self

  def compute(*strands)
    to_a = ->(string) { string.chars.to_a }
    mutations = ->(points) { points.all? && !points.uniq.one? }
    b, a = strands.map(&to_a).sort
    a.zip(b).count(&mutations)
  end
end
