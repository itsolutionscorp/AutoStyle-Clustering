require 'prime'

class Raindrops
  def self.convert num
    have_prime = []
    prime_numbers = num.prime_division

    prime_numbers.each do |nums|
      have_prime << 'Pling' if nums.include? 3
      have_prime << 'Plang' if nums.include? 5
      have_prime << 'Plong' if nums.include? 7
    end

    have_prime.empty? ? num.to_s : have_prime.join
  end
end
