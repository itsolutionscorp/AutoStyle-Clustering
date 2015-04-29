class SumOfMultiples

  def initialize *args
    @multiples = args
  end

  def self.to to
    SumOfMultiples.new(3,5).to(to)
  end

  def to to
    (0...to).select do |i| 
      @multiples.any? { |x| i % x == 0 } 
    end.reduce(&:+)
  end

end
