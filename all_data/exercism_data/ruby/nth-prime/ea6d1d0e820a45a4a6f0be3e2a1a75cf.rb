require 'prime'

class Prime

  def nth(num)
    raise ArgumentError if num < 1
    first(num).last
  end
  
end
