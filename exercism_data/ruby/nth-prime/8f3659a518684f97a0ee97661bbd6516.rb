require 'prime'
class Prime

  def self.nth num
    raise ArgumentError unless num > 0
    Prime.take(num).last
  end

end
