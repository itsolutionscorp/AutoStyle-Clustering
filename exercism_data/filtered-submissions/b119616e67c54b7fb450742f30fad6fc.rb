class Hamming
  def compute(w1, w2)
    counter = 0
    w1.length.times do |i|
      if (w1[i] != w2[i])
        counter += 1
      end
    end
    counter
  end
end
