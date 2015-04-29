class Hamming
  def self.compute(s1, s2)
    distance = 0
    zipped = s1.to_a.zip(s2.to_a)
    zipped.each do |i|
      break          if i[0].nil? || i[1].nil?
      distance += 1 if i[0] != i[1]
    end
    return distance
  end
end

class String
  def to_a
    self.split('')
  end
end
