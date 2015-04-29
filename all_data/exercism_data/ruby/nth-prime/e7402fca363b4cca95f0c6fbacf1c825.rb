require 'prime'
class Prime
  def self.nth(position)
    raise ArgumentError if position == 0
     Prime.first(position)[position-1]
  end
end
