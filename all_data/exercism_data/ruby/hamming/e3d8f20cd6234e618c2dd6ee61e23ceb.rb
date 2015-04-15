class Hamming

  def self.compute(x,y)
    results = 0
    while x != ""
      if x[0] == y[0]
        results += 0
        x.slice!(0)
        y.slice!(0)
      else
        x[0] != y[0]
        results += 1
        x.slice!(0)
        y.slice!(0)
      end

    end
    results
  end


end
