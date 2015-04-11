require "prime"

class Prime
  def nth n
    Prime.first(n).last or raise ArgumentError
  end
end
