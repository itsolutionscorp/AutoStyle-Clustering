require 'prime'

class Prime
  def nth(index)
    raise ArgumentError if index < 1
    i = 1
    each do |prime|
      return prime if i == index
      i += 1
    end
  end
end
