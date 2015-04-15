class Hamming
  def self.compute(a,b)
    distance=0
    [a.length,b.length].min.times do |i|
      distance+=1 unless a[i]==b[i]
    end
    distance
  end
end
