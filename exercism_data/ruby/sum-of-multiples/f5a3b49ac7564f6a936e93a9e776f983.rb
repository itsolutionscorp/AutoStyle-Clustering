class SumOfMultiples
  def initialize *factors
    @factors = factors.empty? ? [3, 5] : factors
  end

  def to max
    (1..max-1).select do |i|
      true if @factors.map{|factor| i%factor==0}.include? true
    end.reduce(0, :+)
  end

  def self.to max
    SumOfMultiples.new.to max
  end
end
