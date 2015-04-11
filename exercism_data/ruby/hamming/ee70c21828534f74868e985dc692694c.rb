require_relative './arrayable'

class Hamming
  using Arrayable

  def self.compute(prim, sec)
    return 0 if prim == sec
    a = prim.to_a
    b = sec.to_a
    a.zip(b).select do |(x, y)|
      x != y if y && x
    end.count
  end
end

# module Arrayable
#   refine String do
#     def to_a
#       split("")
#     end
#   end
# end
