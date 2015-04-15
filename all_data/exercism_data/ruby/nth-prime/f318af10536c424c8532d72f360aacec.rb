require 'prime'
class Prime
  def nth(index)
    raise ArgumentError if index == 0
    Prime.first(index).last
  end
end
