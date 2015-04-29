module Accumulate

  def accumulate
    count = 1
    self.each_with_index do |number, index|
      self[index] = yield(number)
    end
      
  end

end

class Array
  include Accumulate
end
