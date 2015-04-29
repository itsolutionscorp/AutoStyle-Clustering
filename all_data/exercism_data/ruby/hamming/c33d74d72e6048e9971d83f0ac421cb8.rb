class Hamming
  def self.compute(a, b)
    distance = 0
    min_length = [a.length, b.length].min
    
    min_length.times do |n|
      distance +=1 if a[n] != b[n]
    end
    distance
  end
end
