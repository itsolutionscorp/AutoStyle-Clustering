class Hamming
  def self.compute a, b
    a = a.split('')
    b = b.split('')
    distance = 0
    points = [ a.size, b.size ].min
    points.times { distance += 1 if a.shift != b.shift }
    distance
  end
end
