module Hamming
  def compute(a,b)
    a = a.split('')
    b = b.split('')
    a.zip(b).count{|x| x[0] != x[1]}
  end
end
