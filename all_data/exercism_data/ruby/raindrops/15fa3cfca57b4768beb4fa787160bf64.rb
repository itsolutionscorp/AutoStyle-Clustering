require "awesome_print"
class Raindrops < Struct.new(:number)
  def self.convert(n)
    new(n).convert
  end

  def convert
    (words.any? and words.join) or number.to_s
  end

protected

  def words
    @words ||= prime_factor_strings.map { |prime, word|
      (contains_prime_factor?(prime) and word) or nil
    }.compact
  end

  def prime_factor_strings
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end

  def contains_prime_factor? *factors
    factors.any? { |factor| number % factor == 0 }
  end
end
