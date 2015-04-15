class Prime
  include Enumerable
  def self.nth(n)
    raise ArgumentError if n < 1
    new.take(n).last
  end

  def each
    (2...Float::INFINITY).each do |n|
      yield(n) if is_prime?(n)
    end
  end

  private

    def is_prime?(n)
      (2..Math.sqrt(n)).none? { |divisor| n % divisor == 0 }
    end
end
