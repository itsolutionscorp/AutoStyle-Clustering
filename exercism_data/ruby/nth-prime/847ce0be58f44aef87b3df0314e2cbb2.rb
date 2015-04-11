require 'prime'

class Prime

  def nth(max)
    raise ArgumentError if max == 0
    Prime.first(max).last
  end
end
