class Hamming

  def self.compute(x,y)

    z = 0

    x.each_char.with_index do |c,i|
      if x[i] != y[i] && y[i] != nil
        z += 1
      end
    end

    return z

  end

end
