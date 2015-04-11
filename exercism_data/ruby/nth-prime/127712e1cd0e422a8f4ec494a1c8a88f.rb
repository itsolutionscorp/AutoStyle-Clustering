require "prime"

class Prime
  def nth(n)
    raise ArgumentError if n.zero?
    Prime.first(n).last
  end
end
