class Hamming

  def compute(x, y)
    count = 0
    x.length.times do |i|
      if !(x[i] == y[i])
        count += 1
      end
    end
    count
  end

end

Hamming.compute('A','B')
