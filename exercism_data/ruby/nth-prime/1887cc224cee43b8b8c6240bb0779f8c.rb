require 'prime'

class Prime
  def self.nth(n)
    Prime.instance.lazy.drop(n-1).next
  end
end
