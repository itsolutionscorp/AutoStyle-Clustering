class Hamming
  def compute(s1, s2)
    [s1.split(//), s2.split(//)].transpose.reject{|x, y|
      x == y
    }.count
  end
end
