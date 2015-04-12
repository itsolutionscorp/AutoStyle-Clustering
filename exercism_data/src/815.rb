# Hamming Distance
# Seth Yanow - Sept 2014

class Hamming

  def compute(strand1, strand2)

    @length = [strand1.length, strand2.length].min - 1
    @distance = 0

    for i in 0..@length
      @distance += strand1[i] == strand2[i] ? 0 : 1
    end

    @distance
  end
end
