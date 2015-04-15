require 'prime'

class Prime
  def nth(num)
      if num == 0
        raise ArgumentError
      else
        Prime.first(num).pop
    end
  end
end
