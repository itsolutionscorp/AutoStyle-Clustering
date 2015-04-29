class Hamming
  def self.compute(first, second)
    f1, f2 = first.split(''),second.split('')
    (0..lowest_count(f1,f2)-1).count {|_|  f1[_] != f2[_]}
  end

  def self.lowest_count(f1,f2); [f1.count, f2.count].min end
end
