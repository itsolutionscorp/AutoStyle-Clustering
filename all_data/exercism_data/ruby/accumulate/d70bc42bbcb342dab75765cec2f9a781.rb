module Accumulate

  def accumulate(&block)
    reduce([]) do |accu, current|
      accu + [block.call(current)]
    end
  end
end

class Array
  include Accumulate
end
