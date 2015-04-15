require 'prime'

class Raindrops

  def self.convert(number)

    prime_nums = Prime.prime_division(number)
    primes = []
    prime_nums.each do |array|
      array.each do |sub_array|
        primes << sub_array
      end
    end
    primes.uniq

    @pling_plang = ""

    if    primes.include?(3)
      @pling_plang << "Pling"
    end

    if primes.include?(5)
      @pling_plang << "Plang"
    end

    if primes.include?(7)
      @pling_plang << "Plong"
    end

    if @pling_plang.empty?
      p number.to_s
    else
      p @pling_plang.to_s
    end
  end
end
