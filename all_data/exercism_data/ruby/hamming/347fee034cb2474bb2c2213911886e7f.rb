# Hamming Distance
# Seth Yanow - Sept 2014

class Hamming
  def self.min_length(first, second)
    return second.length if first.length > second.length
    return first.length if first.length < second.length
    first.length
  end

  def self.compute(strand1, strand2)

    @length = min_length(strand1, strand2)
    @distance = 0

    for i in 0..@length - 1 # for a zero-based array index our max is one less
      @distance += strand1[i] == strand2[i] ? 0 : 1
    end

    @distance
  end
end
