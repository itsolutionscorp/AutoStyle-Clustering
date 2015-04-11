require 'prime'

class Prime
  def nth(i)
   raise ArgumentError if i == 0

   Prime.first(i).last
  end
end
