class Prime
  require 'prime'

  def self.nth(n)
    raise(ArgumentError, "wat?") if n.zero?
    Prime.take(n).last
  end
end
