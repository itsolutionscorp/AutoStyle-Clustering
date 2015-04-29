class Prime
  require 'prime'
  def self.nth(n)
    raise ArgumentError if n <= 0
    Prime.first(n).last
  end
end
