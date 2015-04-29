require 'prime'

class Prime
  def self.nth(n)
    if n.zero?
      raise ArgumentError
    end
    Prime.first(n).last
  end
end
