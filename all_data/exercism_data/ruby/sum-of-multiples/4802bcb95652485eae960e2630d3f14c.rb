class SumOfMultiples
  def initialize(*args)
    @matchers = args
  end

  def to(limit)
    (0...limit).inject(0) do |sum, num|
      @matchers.each { |matcher| sum += num and break if num % matcher == 0 }
      sum
    end
  end

  def self.to(limit)
    SumOfMultiples.new(3, 5).to(limit)
  end
end
