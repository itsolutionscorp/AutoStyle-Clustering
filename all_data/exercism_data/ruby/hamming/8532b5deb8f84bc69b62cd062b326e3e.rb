class Hamming
  def self.compute(a,b)
    distance = 0
    index = 0
    a.each_char do |c|
      distance = distance + 1 unless c == b[index]
      index = index + 1
    end
    distance
  end
end
