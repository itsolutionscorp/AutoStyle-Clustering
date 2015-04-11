class Raindrops
  def self.convert(integer)
    new(integer).convert
  end

  def initialize(integer)
    @integer = integer
  end

  def convert
    NumbersToRaindropsConverter.new(prime_factors, @integer).convert
  end

  private

  def prime_factors
    PrimeFactorsFinder.new(@integer).find
  end
end

class PrimeFactorsFinder
  def initialize(integer)
    @integer = integer
  end

  def find
    factor(@integer).flatten.sort
  end

  private

  def factor(n)
    return [] if n <= 1
    prime = [2, 3, 5, 7, n].select { |p| n % p == 0 }
    return [prime] + factor(n.div(prime.first))
  end
end

class NumbersToRaindropsConverter
  def initialize(numbers, original)
    @numbers = numbers
    @original = original
  end

  def convert
    retval = ""
    retval << "Pling" if @numbers.include?(3)
    retval << "Plang" if @numbers.include?(5)
    retval << "Plong" if @numbers.include?(7)
    retval.empty? ? @original.to_s : retval
  end
end

class RaindropConvertParamNotInteger < StandardError; end
