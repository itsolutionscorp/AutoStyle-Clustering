require 'prime'

class Prime
  def self.nth(input)
    raise ArgumentError if input == 0
    self.first(input).last
  end
end
