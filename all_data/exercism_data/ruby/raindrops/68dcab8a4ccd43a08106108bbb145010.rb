class Raindrops

  def self.convert(num)
    str = ""

    prime_factors = {3=> "Pling", 5=> "Plang", 7=> "Plong"}

    prime_factors.each do |key,_|
      str << prime_factors[key] if num % key == 0
    end

    str = num.to_s if str.empty?

    str
  end

end
