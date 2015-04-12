class Hamming

  def compute(x, y)
  	distance = 0
    x.length.times do 
      distance += 1 if letter != y[index]
    end
    distance 
  end

end
