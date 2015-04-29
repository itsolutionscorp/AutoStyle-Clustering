require 'prime'

class Prime

  def nth(number)
    fail ArgumentError, 'must be a valid number' if number < 1
    first(number).last
  end

end
