class Prime
  def self.nth(x)
    raise ArgumentError if x == 0
    prime[x -1]
  end

  def self.prime
    universe = (2..104_743).to_a
    universe.each do |p|
      universe.reject! {|i| i % p == 0 and i != p}
    end
  end
end
