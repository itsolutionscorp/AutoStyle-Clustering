PRIMERS = { 3 => "Pling", 5 => "Plang", 7 =>"Plong" }

class Raindrops
  def self.convert(i)
    result = PRIMERS.inject(""){ |result,prime| result += prime[1] if (i % prime[0])==0 ; result}
    result = i.to_s if result === ""
    result
  end
end
