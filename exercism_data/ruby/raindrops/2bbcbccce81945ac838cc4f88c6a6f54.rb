class Fixnum
  def factors(range = 0..100)
    range.select do |x|
      range.any? {|y| x * y == self }  
    end
  end

  def prime_factors(range = 0..100)
    factors(range).select(&:is_prime?)
  end

  def is_prime?
    self % 2 != 0
  end
end

Raindrops = Struct.new(:number) do
  def self.convert(number)
    new(number).convert
  end

  def convert
    prime_factors = number.prime_factors

    output = ""
    output << "Pling" if prime_factors.include? 3
    output << "Plang" if prime_factors.include? 5
    output << "Plong" if prime_factors.include? 7
    output << number.to_s if output.empty?

    output
  end
end
