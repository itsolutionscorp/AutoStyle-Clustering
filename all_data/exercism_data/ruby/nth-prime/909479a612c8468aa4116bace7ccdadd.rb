require 'prime'

class Prime
  def nth(number)
    if number > 0
      self.first(number).last
    else
        raise ArgumentError
    end
  end
end
