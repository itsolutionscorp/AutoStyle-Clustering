require 'prime'

# Extend the Prime class to include a nth method
class Prime
  def nth(number)
    fail ArgumentError if number == 0
    first(number).pop
  end
end
