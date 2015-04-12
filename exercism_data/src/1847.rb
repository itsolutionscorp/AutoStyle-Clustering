module Hamming
  def compute(one, other)
    if one.length == 1
      if one == other
        0
      else
        1
      end
    else
      compute(one[0], other[0]) + compute(one[1..-1], other[1..-1])
    end
  end
end
