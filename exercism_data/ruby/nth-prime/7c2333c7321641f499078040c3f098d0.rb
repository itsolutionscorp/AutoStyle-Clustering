require 'prime'

class Prime
  def self.nth index
    raise ArgumentError if index < 1
    Prime.first(index).last
  end
end
