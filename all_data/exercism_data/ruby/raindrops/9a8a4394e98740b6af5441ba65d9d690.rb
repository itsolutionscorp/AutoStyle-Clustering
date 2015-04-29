class Raindrops < Struct.new(:number)
  def self.convert(number)
    new(number).convert
  end

  def convert
    str = ""
    str += "Pling" if contains_prime_factor? 3
    str += "Plang" if contains_prime_factor? 5
    str += "Plong" if contains_prime_factor? 7
    str += "#{number}" unless contains_prime_factor? 3, 5, 7
    str
  end

protected

  def contains_prime_factor? *factors
    factors.any? { |factor| number % factor == 0 }
  end
end
