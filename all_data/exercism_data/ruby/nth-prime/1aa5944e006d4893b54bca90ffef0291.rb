require 'prime'

class Prime
  def self.nth position_num
    raise ArgumentError.new("There is no such") if (position_num < 1)
    (first position_num).last
  end
end
