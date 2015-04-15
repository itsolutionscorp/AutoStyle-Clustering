require 'prime'

class Prime
  def self.nth(n)
    if n == 0
      raise ArgumentError
    else
      (Prime.first n).last
    end
  end
end
