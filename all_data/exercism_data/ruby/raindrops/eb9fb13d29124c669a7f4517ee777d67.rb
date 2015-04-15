class Raindrops
  def initialize(raindrops)
    @raindrops = raindrops
    calculate_primes
  end

  def to_s
    if pling? || plang? || plong?
      pling + plang + plong
    else
      @raindrops.to_s
    end
  end

  def self.convert(number)
    raindrops = Raindrops.new(number)
    raindrops.to_s
  end

  private

  def calculate_primes
    @primes = (1..@raindrops).lazy.select { |n| @raindrops % n == 0 }
  end

  def pling?
    @primes.include?(3)
  end
  def pling
    pling? ? 'Pling' : ''
  end

  def plang?
    @primes.include?(5)
  end
  def plang
    plang? ? 'Plang' : ''
  end

  def plong?
    @primes.include?(7)
  end
  def plong
    plong? ? 'Plong' : ''
  end
end
