require 'prime'

class Prime
  def nth(n)
    raise ArgumentError, "Not valid" unless ( n > 0 and n.is_a? Integer )
    (Prime.first n).last
  end
end
