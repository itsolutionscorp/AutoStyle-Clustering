# version 2 (typo in v1)

require 'prime'

class Prime
  
  def nth(index)
    if index <= 0
      raise ArgumentError
    else
      Prime.take(index).last
    end
  end
  
end
