class Prime
  @known_primes = [2]
  def self.nth n
    raise ::ArgumentError.new("Expected n >= 1. Got #{n}.") if n <= 0

    (1..n-1).each do |i|
      if @known_primes[i].nil? then
        (@known_primes[i-1]..@known_primes[i-1]*2-1).each do |v|
          divisible = false
          @known_primes.each do |prime|
            if v % prime == 0 then
              divisible = true
              break
            end
          end
          if not divisible then
            @known_primes[i] = v
            break
          end
        end
      end
    end

    @known_primes[n-1]
  end
end
