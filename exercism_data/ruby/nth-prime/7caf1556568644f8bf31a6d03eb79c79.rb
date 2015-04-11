require 'prime'

class Prime
  def self.nth(int)
    int == 0 ? (raise ArgumentError) : self.first(int).last
  end
end
