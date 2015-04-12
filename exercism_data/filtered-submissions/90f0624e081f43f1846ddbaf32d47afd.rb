class Hamming
  def compute(x, y)
    hamming = 0
    x.each_char.with_index do |char, idx|
      hamming += 1 unless char == y[idx] 
    end
    return hamming
  end
end
