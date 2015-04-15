# Module uses the Prime Sieve method.
# Read more here: http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
module Prime
  def self.nth n
    raise ArgumentError if n.to_i < 1

    # Got no theoretical proof for this upper bound.
    # But, try running the following in Wolfram Alpha:
    #   Range[floor(n^(1.33) - prime(n) + 10) for n in (2, 20000)]
    # Or, visit: http://bit.ly/1rTHF6s
    # Replace, 'Range' with 'Graph' to see that function is increasing.
    upper_bound = (n ** (1.33)).to_i + 10
    sieve = (2..upper_bound).to_a
    p = 2
    while true
      sieve = sieve.delete_if{ |k| k % p == 0 && k != p }
      p = sieve.detect{|k| k > p}
      break unless p
    end

    sieve[n - 1]
  end
end
