class Hamming
  def self.compute(x,y)
    distance = 0
    x.length.times do |index|
      distance+=1 if x[index] != y[index]
    end
    distance
  end
end
