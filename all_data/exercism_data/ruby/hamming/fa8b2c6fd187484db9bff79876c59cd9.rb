class Hamming

  def self.compute(strand1, strand2)
    HammingDistance.new(strand1, strand2).distance
  end

end

class HammingDistance

  def initialize(strand1, strand2)
    @strand1 = strand1.to_s
    @strand2 = strand2.to_s
    order_strands_by_length
  end

  def distance
    count_differences
  end

  private

    def order_strands_by_length
      swap if @strand1.length > @strand2.length
    end

    def swap
      @strand1, @strand2 = @strand2, @strand1
    end

    def count_differences
      @strand1.chars.each_with_index.count do |c, i|
        !@strand2[i].eql?(c)
      end
    end

end
