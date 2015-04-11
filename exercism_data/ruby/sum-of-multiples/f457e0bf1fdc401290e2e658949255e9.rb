class SumOfMultiples
  # attr_accessor :num

  # def initialize *arg
  #   @num = arg
  # end

  def self.to num
    arg = []
    0.upto(num-1) do |i|
      arg << i if i % 3 == 0 || i % 5 == 0
    end
    arg.reduce(:+)
  end

  # def to n
  #   #what does configurable mean?
  # end
end

#puts SumOfMultiples.to(4)
#puts SumOfMultiples.new(7,13,17).to(20)
