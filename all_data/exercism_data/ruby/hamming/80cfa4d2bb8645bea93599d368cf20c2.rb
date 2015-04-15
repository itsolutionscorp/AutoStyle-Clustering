class Hamming
  def self.compute(first, second)
    f1, f2 = first.chars,second.chars
    (0..[f1.count, f2.count].min-1).count {|_|  f1[_] != f2[_]}
  end
end
