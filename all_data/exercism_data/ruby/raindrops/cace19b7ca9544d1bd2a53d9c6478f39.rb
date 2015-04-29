require 'prime'

class Raindrops
  @@language = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def initialize(number)
    @number = number
  end

  def lookup(letter)
    @@language[letter]
  end

  def convert
    if translations.empty?
      @number.to_s
    else
      translations.join
    end
  end

  def primes
    Prime.prime_division(@number).transpose.first || []
  end

  def translations
    @translations ||= primes.map{|prime| lookup(prime) }.compact
  end

  def self.convert(number)
    new(number).convert
  end
end
