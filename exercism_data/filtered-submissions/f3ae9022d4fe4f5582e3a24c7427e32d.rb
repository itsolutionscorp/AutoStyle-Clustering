class Hamming
  def compute(a, b)
    a = a.split("")
    b = b.split("")
    a.zip(b).count{|pair| pair.compact.uniq.size > 1 }
  end
end
