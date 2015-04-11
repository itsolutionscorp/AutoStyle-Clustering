class Hamming
  
  def self.compute d1, d2
    Hamming.new(d1, d2).distance
  end

  def initialize d1, d2
    @d1 = d1
    @d2 = d2
  end

  def distance
    (0..@d1.length).count do |i|
      @d1[i] != @d2[i]
    end
  end
end
