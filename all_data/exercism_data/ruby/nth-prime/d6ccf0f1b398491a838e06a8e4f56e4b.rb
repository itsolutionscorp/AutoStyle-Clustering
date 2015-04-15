require 'prime'
class Prime
  def nth(n)
    self.lazy.take(n).drop(n - 1).first
  end
end
