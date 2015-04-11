class Sieve

  def initialize tops
    @possibles = [*2..tops]
  end

  def primes
    @possibles
    primes = []
    begin
      latest = @possibles.shift
      primes << latest
      @possibles.reject! { |i| i % latest == 0 }
    end until @possibles.empty?
    primes
  end

end
