class Hamming
  def self.compute(x,y)
    mutations = 0
    [x.length, y.length].min.times do |i|
      mutations += 1 if x[i] != y[i]
    end

    mutations
  end
end
