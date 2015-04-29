require 'prime'

class Prime

  def self.nth(nth)
    if nth > 0
      self.first(nth).last
    else
      raise ArgumentError, "Number must be positive integer"
    end
  end

end
