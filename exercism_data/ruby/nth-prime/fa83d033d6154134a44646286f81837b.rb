require "prime"
StdlibPrime = Object.send(:remove_const, :Prime)

module Prime
  def self.nth(index)
    raise(ArgumentError, "Bad index") unless index > 0
    StdlibPrime.first(index).last
  end
end
