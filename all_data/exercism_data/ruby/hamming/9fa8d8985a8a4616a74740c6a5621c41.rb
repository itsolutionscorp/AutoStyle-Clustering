class Hamming
  def self.compute(x,y)
    [].tap do |distance|
      x.length.times do |index|
        distance << (x[index] != y[index])
      end
    end.count(true)
  end
end
