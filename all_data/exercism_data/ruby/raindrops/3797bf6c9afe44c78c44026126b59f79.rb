class Raindrops
    def self.convert(n)
        [3, 5, 7]                       # the array of primes to consider
        .each_with_index                # pack index with each of those primes
        .select { |p, i|                # select primes based on
            n % p == 0                  # whether it divides n or not
        }
        .map { |p, i|                   # map selected prime number to
            %w{Pling Plang Plong}[i]    # "Pling", "Plang", "Plong" using index
        }
        .inject(&:+) or                 # add up all such strings if any exists
        n.to_s                          # otherwise return the number itself
    end
end
