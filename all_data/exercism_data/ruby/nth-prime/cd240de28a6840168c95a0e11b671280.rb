require 'mathn'

class Prime

  def self.nth(index)
    fail ArgumentError, 'must be positive' if index < 1

    self.first(index).last
  end

end
