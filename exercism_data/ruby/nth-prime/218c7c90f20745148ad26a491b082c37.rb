# class to work out nth prime
class Prime
  def self.nth(n)
    raise ArgumentError if n.zero?
    range = 2..Float::INFINITY
    range.reduce(0) do |res, x|
      return x-1 if res == n; # need to get rid of this magic -1
      is_prime?(x) ? res += 1 : res
    end
  end

  def self.is_prime?(n)
    (2...n).none? { |i| (n % i) == 0 } # simplest brute force prime check
  end
end
