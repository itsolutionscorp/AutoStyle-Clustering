require 'prime'

class Prime

  def self.nth num
    raise ArgumentError if num.eql? 0
    Prime.first(num)[-1]
  end

end
