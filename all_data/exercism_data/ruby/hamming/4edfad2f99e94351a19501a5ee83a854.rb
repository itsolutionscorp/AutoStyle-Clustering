class Hamming
  def self.compute(a,b)
    new(a,b).compute
  end

  def initialize(a, b)
    @long_strand = a.size > b.size ? a : b
    @small_strand = a.size <= b.size ? a : b
  end

  def compute
    distance = 0

    return distance if @long_strand.nil? || @small_strand.nil?

    @small_strand.split("").each_with_index do |c, index|
      distance += 1 if @long_strand[index] != c
    end

    distance
  end
end
