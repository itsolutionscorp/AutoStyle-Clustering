require 'prime'

class Prime
  def nth(n)
    if n < 1 then raise(ArgumentError, 'Argument must be greater than 0.')  end
    Prime.first(n).last
  end
end
