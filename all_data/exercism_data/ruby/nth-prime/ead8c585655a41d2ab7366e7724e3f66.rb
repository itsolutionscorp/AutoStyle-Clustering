# version 1
# AugmentError exception doesn't work

require 'prime'

class Prime
  
  def nth(index)
    if index <= 0
      raise AugmentError
    else
      Prime.take(index).last
    end
  end
  
end
