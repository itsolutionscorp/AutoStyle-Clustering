require 'prime'
class Prime
  def nth(n)
    first(n).last || fail(ArgumentError)
  end
end
