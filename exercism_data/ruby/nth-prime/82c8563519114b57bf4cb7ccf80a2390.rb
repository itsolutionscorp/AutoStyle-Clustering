class Prime
  def self.nth(n)
    raise ArgumentError unless (n.is_a? Integer) && n > 0
    # Upper Bound:
    # http://functions.wolfram.com/NumberTheoryFunctions/Prime/29/0002/
    upper_bound = n > 5 ? (n*Math.log(n*Math.log(n))).ceil : 11
    # Sieve of Eratosthenes up to upper bound to get at least n primes
    nums = [nil, nil, *2..upper_bound]
    (2..Math.sqrt(upper_bound)).select { |x| !nums[x].nil? }.each do |prime|
      (prime**2..upper_bound).step(prime) { |mult| nums[mult] = nil }
    end
    nums.compact[n-1]
  end
end
