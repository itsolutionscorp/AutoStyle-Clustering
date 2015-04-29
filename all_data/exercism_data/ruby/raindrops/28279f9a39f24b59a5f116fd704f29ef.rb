class Raindrops
  PRIMES = [3,5,7].freeze
  SOUNDS = %w{Pling Plang Plong}.freeze

  def self.convert(num)
    available = PRIMES.dup

    until available.empty? || num < available.min
      found = check_divisibility(num, available)
      break unless found

      num, used = *found
      available.delete(used)
    end

    matches = PRIMES - available
    return num.to_s if matches.empty?
    matches.reduce("") {
      |str, prime| str << SOUNDS[PRIMES.index(prime)]
    }
  end

  private

  def self.check_divisibility(num, primes)
    primes.each do |prime|
      divided = num.to_f / prime.to_f
      return [divided, prime] if divided == divided.to_i
    end
    false
  end

end
