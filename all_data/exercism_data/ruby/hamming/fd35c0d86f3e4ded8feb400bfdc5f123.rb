class Hamming
  def self.compute(a, b)
    a,b = b,a if b.length < a.length
    distance = 0
    a.chars.each_with_index do |l, i|
      distance += 1 if b[i] != l
    end
    distance
  end
end
