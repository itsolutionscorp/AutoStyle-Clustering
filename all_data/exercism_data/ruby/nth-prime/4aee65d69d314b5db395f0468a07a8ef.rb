require 'prime'
class Prime
  def self.nth num
    raise ArgumentError if num < 1
    first(num).last
  end
end
