require "prime"

class Prime
  def nth(n)
    if n < 1 || n != n.to_i
      raise ArgumentError, "n must be an integer greater than 0"
    end

    self.class.instance.each do |prime|
      return prime if (n -= 1).zero?
    end
  end
end
