require 'prime'

####
# Converts a number to a string
#
# The contents of the string depends on primefactors
# - a factor - is a number that divides into another
#              number without leaving a remainder
# - Primefactor - a factor that is a prime ;-)
#
####
class Raindrops

  def convert number
    if primes_in_raindrop? number
      primes_to_raindrop number
    else
      pass_through number
    end
  end

  private

  def primes_in_raindrop? number
    prime_factors_of(number)
          .any? { |prime_factor| rain_drop_number? prime_factor }
  end

  def primes_to_raindrop number
    prime_factors_of(number)
          .map { |prime_factor| to_raindrop prime_factor }.join
  end

  def pass_through number
    number.to_s
  end

  def prime_factors_of number
    number.prime_division.map { |prime_factor_set| prime_factor_set.first}
  end

  RAIN_DROP_CONVERTABLE = [3, 5, 7]

  def rain_drop_number? candidate
    RAIN_DROP_CONVERTABLE.include? candidate
  end

  def to_raindrop candidate
    case candidate
      when 3 then 'Pling'
      when 5 then 'Plang'
      when 7 then 'Plong'
      else nil
    end
  end
end
