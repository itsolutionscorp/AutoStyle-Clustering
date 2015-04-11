require 'prime'

class Prime
  def self.nth(count)
    raise ArgumentError if count < 1
    detect.with_index {|_, i| i == count - 1 }
  end
end
