class SumOfMultiples

  def initialize(*multiples)
    @multiples = multiples
    @values = []
  end

  def to(limit)
    (0...limit).collect do |num|
      @multiples.each do |multiple|
        if (num % multiple === 0)
          @values << num
          break
        end
      end
    end
    @values.reduce(:+)
  end

  def self.to(limit)
    self.new(3,5).to(limit)
  end

end
