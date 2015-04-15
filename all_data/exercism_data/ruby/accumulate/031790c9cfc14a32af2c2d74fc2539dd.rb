module Accumulate
  def accumulate(&block)
    [].tap do |result|
      each do |elem|
        result << block.yield(elem)
      end
    end
  end
end

Array.include Accumulate
