require 'prime'
class Prime
  def self.nth(prime_count)
    raise ArgumentError if prime_count.zero?
    self.first(prime_count).last
  end
end
