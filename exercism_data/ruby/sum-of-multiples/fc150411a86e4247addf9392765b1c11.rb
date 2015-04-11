class SumOfMultiples
  def initialize(*args)
  	@multiples = args
  end

  def to(max)
  	(0..max-1).select do |i|
  		@multiples.any? { |x| i % x == 0 }
  	end.reduce(&:+)
  end

  def self.to(max)
    new(3, 5).to(max)
  end
end
