require 'prime'

class Prime

  def nth(num)
    if num < 1
      raise ArgumentError.new('Value must be greater than 0.')
    end
    Prime.first(num).last
  end

end
