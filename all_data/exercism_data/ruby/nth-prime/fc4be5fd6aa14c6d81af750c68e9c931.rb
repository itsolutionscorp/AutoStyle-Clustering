require 'prime'

class Prime

  def self.nth(n)
    if n == 0
      raise ArgumentError.new("Only positive numbers allowed")
    end
    (Prime.first n).last
  end

end
