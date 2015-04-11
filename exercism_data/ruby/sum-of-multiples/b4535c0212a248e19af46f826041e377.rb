class SumOfMultiples
  attr_accessor :multiples
  class << self
    def to(num,multiples=[3,5])
      (1...num).select{|x| multiples.any?{|y| (x%y).zero?}}.reduce(0){|x,y| x+y}
    end
  end

  def initialize(*args)
    self.multiples=args
  end

  def to(num)
    self.class.to(num,multiples)
  end
end
