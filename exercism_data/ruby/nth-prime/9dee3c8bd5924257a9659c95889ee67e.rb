require 'prime'
class Prime

  def nth(n)
    if n == 0
      raise ArgumentError
    else
      (Prime.first n).last
    end
  end
end
