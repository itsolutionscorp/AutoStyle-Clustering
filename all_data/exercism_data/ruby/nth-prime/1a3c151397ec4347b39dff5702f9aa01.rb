require 'prime'

class Prime
  def self.nth(index)
    raise ArgumentError, "Index must be positive" if index < 1
    lazy.first(index).last
  end
end
