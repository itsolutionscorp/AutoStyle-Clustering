class Raindrops
  require 'prime'
  def self.convert num
    have_prime = []
    prime_numbers = num.prime_division

    prime_numbers.each do |nums|
      have_prime << 'Pling' if nums.include? 3
      have_prime << 'Plang' if nums.include? 5
      have_prime << 'Plong' if nums.include? 7
    end

    if have_prime.length > 0
      have_prime.join
    else
      num.to_s
    end
  end
end
