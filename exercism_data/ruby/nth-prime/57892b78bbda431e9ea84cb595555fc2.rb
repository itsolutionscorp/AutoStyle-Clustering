require 'prime'

class Prime

  def self.nth(nth_number)
    raise ArgumentError, 'nth number must be greater than 1' if nth_number < 1
    (Prime.first nth_number).last
  end

end
