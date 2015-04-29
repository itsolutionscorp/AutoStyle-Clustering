require 'prime'
class Prime
  def nth(p)
    raise ArgumentError if p == 0
    first(p).last
  end
end
