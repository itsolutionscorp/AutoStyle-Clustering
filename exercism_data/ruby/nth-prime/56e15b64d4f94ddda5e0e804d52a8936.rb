require 'prime'

class Prime
  def nth(n)
    fail ArgumentError if n.zero?
    take(n).last
  end
end
