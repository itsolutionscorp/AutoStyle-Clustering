require "prime"

class Prime
  def self.nth(n)
    if n.zero?
      raise ArgumentError, "0th prime does not exist"
    end

    take(n).last
  end
end
