require 'prime'

class Prime
  def nth number
    Prime.first(number).last
  end
end
