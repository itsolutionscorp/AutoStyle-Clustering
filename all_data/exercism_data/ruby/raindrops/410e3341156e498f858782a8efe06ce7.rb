class Raindrops

  @sounds = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
  
  def self.convert(num)  
    answer = prime_factors(num).map { |factor| @sounds[factor] }.join
    answer.length > 0 ? answer : num.to_s
  end

  private  
    def self.prime_factors(num)
      [3,5,7].each.select { |prime| num % prime == 0} 
    end

end