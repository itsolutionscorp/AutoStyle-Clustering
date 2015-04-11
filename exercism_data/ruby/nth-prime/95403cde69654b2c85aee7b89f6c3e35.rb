class Prime
  def nth(nth_prime)
    fail ArgumentError if nth_prime < 1
    num = 2
    primes = []
    while primes.length < nth_prime
      if primes.map { |x| num.divmod(x)[1] == 0 }.uniq.include? true
        num += 1
      else
        primes << num
        num += 1
      end
    end

    primes.last
  end
end

__END__

This is obviously very slow for very large values.
Can't be bothered to implement a faster algo.
