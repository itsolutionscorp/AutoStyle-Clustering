class Hamming

  def compute(x,y)

    return 0 if x == y

    if x.size == y.size || x.size < y.size
      hd = 0
      x.length.times do |i|
        x[i] == y[i] ? nil : hd += 1
      end
      return hd
    elsif
      hd = 0
      y.length.times do |i|
        x[i] == y[i] ? nil : hd += 1
      end
      return hd
    end

  end

end
