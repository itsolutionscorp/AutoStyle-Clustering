# extend Array implementing accumulate without using map, reduce etc
class Array
  def accumulate
    result = []
    self.each { |i| result << yield(i) }
    result
  end
end
