class Raindrops
  def self.convert number
    prime_hash = { 3 => "Pling" , 5 => "Plang" , 7 => "Plong" }
    converted = prime_hash.reduce("") do |converted, (key, value)| 
      converted + (number % key == 0 ?  value : "")
    end
    converted == "" ? number.to_s : converted
  end
end
