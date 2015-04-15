class Raindrops
  def self.convert(number)
    prime_factors = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
    
    numstr = prime_factors.reduce("") do | memo, (prime_num, prime_string) |
      (number % prime_num == 0) ? memo += prime_string : memo
    end

    numstr.empty? ? number.to_s : numstr
  end
end
