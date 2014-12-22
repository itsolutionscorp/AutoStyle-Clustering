class Array
  
  def module 
    Math.sqrt(self.map { |x| x ** 2 }.reduce(:+))
  end

end

module Enumerable
  
  def sum
    self.reduce(0) { |accum, i| accum + i}
  end

  def mean
    self.sum / self.length.to_f
  end

  def sample_variance
    m = self.mean
    sum = self.reduce(0) { |accum, i| accum + (i-m)**2 }
    sum / (self.length - 1).to_f
  end

  def standard_deviation
    Math.sqrt(self.sample_variance)
  end

end

