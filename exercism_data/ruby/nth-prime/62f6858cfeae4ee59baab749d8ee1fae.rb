require 'prime'

class Prime

  def nth(position)
    if position < 1
      raise ArgumentError.new('Position must be at least 1')
    end
    Prime.first(position)[position - 1]
  end

end
