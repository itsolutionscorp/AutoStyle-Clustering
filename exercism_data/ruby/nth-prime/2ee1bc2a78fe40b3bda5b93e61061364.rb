require 'prime'

class Prime
  def self.nth(i)
   raise ArgumentError if i == 0

   first(i).last
  end
end
