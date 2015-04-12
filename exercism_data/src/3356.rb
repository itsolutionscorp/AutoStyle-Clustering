module Hamming

  def Hamming.compute(a, b, c=0)
    return c if a.length == 0 or b.length == 0
    return compute(a[1..-1], b[1..-1], c) if a[0] == b[0]
    return compute(a[1..-1], b[1..-1], c+1)
  end

end
