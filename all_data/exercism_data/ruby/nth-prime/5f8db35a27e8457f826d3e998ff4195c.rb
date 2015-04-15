require 'prime'

class Prime

  def self.nth(number)
    raise ArgumentError if number.to_i < 1
    Prime.first(number.to_i).last
  end

end
