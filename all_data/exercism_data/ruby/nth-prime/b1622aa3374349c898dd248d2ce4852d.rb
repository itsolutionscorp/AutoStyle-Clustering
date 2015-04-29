require 'Prime'

class Prime
  def self.nth(val)
    if val < 1
      raise ArgumentError
    end
    Prime.first(val).last
  end
end
