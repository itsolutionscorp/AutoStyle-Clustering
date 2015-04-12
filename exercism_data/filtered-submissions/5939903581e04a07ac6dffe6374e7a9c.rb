class Hamming
  def compute(a, b)
    a.split("").zip(b.split "").count { |x| x[0] != x[1] }
  end 
end
