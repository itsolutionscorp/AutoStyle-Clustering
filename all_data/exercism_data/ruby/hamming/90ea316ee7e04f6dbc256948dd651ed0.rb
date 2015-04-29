class Hamming
  def self.compute(first, second)
    f1, f2 = first.split(''),second.split('')
    result = 0
    (0..lowest_count(f1,f2)-1).each {|_| result += 1 unless f1[_].eql? f2[_]}
    result
  end

  def self.lowest_count(f1,f2)
    f1.count > f2.count ? f2.count : f1.count
  end
end
