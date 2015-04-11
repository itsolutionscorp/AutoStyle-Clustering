require 'prime'

class Prime
  def nth(nprime)
    raise ArgumentError.new if nprime <= 0
    first(nprime)[-1] 
  end
end
