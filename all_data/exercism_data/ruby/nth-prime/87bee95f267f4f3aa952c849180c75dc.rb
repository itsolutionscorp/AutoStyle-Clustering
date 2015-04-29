require 'prime'
class Prime
  def self.nth(position)
    raise ArgumentError if position < 1
    Prime.first(position).last
  end
end
