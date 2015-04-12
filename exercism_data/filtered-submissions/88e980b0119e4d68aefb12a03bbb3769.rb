class Hamming
  def compute(s1, s2)
    [s1.split(//), s2.split(//)].transpose.count{|x, y|
      x != y
    }
  end
end
