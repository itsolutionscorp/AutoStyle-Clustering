class Hamming

  def self.compute(a, b)
    hamming = 0
    i = 0
    shortest_length = (a.length < b.length) ? a.length : b.length
    while(i <=  shortest_length - 1)
      hamming += 1 if a[i] != b[i]
      i += 1
    end
    hamming
  end

end
