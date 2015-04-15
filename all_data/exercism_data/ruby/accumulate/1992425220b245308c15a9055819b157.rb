module Accumulate
  def accumulate(&block)
    result = []
    self.each do |elem|
      result << block.yield(elem)
    end
    result
  end
end

Array.include Accumulate
