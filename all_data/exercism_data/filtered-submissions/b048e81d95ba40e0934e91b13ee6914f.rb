# Hamming difference program
module Hamming

  def Hamming.compute(a, b)
    distance = 0

    return distance if (a == b)

    for i in (0...[a.length, b.length].min)
      distance += 1 unless (a[i] == b[i])
    end

    return distance;

  end

end
