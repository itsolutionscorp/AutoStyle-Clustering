class SumOfMultiples
  def initialize (*args)
    @result = args
  end

  def self.to(n)
    new(3,5).to(n)
  end

  def to(n)
    @box = []
    @result.each do |other|
      (0..n -1).each do |number|
        if number % other == 0
          @box << number
        end
      end
    end
    @box.uniq.reduce(:+)
  end
end
