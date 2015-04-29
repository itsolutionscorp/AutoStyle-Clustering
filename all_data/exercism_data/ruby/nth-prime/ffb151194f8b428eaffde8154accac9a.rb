require 'prime'
class Prime
  def nth(n)
    raise ArgumentError if n.zero?
    first(n).last
  end
end
