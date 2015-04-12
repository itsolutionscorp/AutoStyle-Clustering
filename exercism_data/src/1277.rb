module Hamming
  def compute(a, b)
    diff = 0
    (a.size).times do |t|
      index = t - 1
      diff += 1 if a.byteslice(index) != b.byteslice(index)
    end
    diff
  end
end
