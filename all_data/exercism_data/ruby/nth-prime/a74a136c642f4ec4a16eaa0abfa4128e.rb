require 'prime'

class Prime

  def self.nth(value)
    raise ArgumentError unless value > 0
    self.first(value).last
  end

end
