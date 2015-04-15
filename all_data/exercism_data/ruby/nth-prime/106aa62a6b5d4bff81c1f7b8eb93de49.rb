require 'prime'

class Prime
  def self.nth(n)
    if n < 1
      raise ArgumentError
    end

    Prime.take(n)[-1]
  end
end
