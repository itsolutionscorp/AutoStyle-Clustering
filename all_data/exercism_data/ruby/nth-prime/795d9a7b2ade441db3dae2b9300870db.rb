require 'prime'

class Prime
  def self.nth target
    return (Prime.first target).to_a.last unless target <= 0
    raise ArgumentError
  end
end
