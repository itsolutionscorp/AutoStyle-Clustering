module Hamming
  def compute a, b
    a.length.times.count{|i| a[i] == b[i] }
  end
end