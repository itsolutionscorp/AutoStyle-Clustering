module Enumerable
  def accumulate
    result = []
    self.each { |element| result.push(yield(element)) }
    result
  end
end
