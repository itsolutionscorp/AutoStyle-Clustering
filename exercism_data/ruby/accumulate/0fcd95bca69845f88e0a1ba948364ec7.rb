module Accumulator
  
  def accumulate
    Array.new( length ) do |x|
      yield self[ x ]
    end
  end

end

Array.send :include, Accumulator
