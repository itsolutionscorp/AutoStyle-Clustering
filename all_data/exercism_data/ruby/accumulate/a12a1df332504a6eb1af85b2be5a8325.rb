module Accumulator
  
  def accumulate
    self.each_with_index do |x, index|
      self[ index ] = yield x
    end
  end

end

Array.send :include, Accumulator
