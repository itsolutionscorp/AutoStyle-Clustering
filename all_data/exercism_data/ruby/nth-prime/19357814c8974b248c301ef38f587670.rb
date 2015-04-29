require 'prime'
class Prime
  def nth n
    fail ArgumentError if n==0
    Prime.take(n)[-1]
  end
end
