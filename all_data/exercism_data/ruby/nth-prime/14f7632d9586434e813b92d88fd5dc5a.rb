require 'prime'
class Prime
  def self.nth(n)
    # make sure our n > 0 or throw an exception
    n > 0 ? Prime.take(n).last.to_i : raise(ArgumentError)
  end
end
