require "prime"
class Prime
  def self.nth(int)
    raise ArgumentError if int < 1
    Prime.take(int)[int-1]
  end
end
