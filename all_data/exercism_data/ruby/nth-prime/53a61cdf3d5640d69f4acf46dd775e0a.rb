require 'prime'

class Prime
  def nth(n)
    if n > 0
      (Prime.first n).last
    else
      raise ArgumentError
    end
  end
end
