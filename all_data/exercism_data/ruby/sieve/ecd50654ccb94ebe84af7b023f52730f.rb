class Sieve
  attr_reader :primes

  def initialize limit
    self.limit = limit
    self.primes = []
    self.candidates = (2..limit).to_a
    generate_primes
  end

  protected
    attr_accessor :candidates, :limit
    attr_writer :primes

    def generate_primes
      candidates.each do |candidate|
        next if candidate.nil?
        self.primes << candidate
        flag_multiples_of candidate
      end
    end

    # Mark all composite candidates by setting them to nil
    # The index of any given candidate is candidate - 2
    def flag_multiples_of number
      composite = number * 2
      while composite <= limit
        self.candidates[composite - 2] = nil
        composite += number
      end
    end
end
