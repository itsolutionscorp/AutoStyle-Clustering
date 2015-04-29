require 'prime'

class Prime
  def nth(num)
    unless num > 0
      raise ArgumentError.new("Only positive integers are allowed.")
    end
    Prime.first(num).last
  end
end
