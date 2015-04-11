require "Prime"

class Prime

  def nth(n)
    n > 0 ? Prime.first(n)[n-1] : raise(ArgumentError)
  end

end
