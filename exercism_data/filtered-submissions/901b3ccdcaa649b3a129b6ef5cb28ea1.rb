class Hamming
  def compute(strandA, strandB)

    length = [strandA, strandB].min.length - 1
    data = strandA.split('')[0..length].zip(strandB.split('')[0..length])

    data.count do |(pointA, pointB)|
      pointA != pointB
    end

  end

end
