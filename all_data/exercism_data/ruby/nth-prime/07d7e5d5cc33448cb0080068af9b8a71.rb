require 'prime'

class Prime
  def self.nth position
    fail ArgumentError if position < 1

    take(position).last
  end
end
