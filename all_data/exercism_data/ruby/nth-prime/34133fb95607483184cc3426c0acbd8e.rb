class Prime

require 'prime'

  def self.nth(val)
    raise ArgumentError, "enter positive integer" if val <= 0
    Prime.first(val)[val-1]
  end

end
