class Hamming
  def self.compute(lhs, rhs)
    throw ArgumentError.new("Arguments of inequal length.") if lhs.length != rhs.length
    lhs.length.times.count {|i| lhs[i] != rhs[i]}
  end
end
