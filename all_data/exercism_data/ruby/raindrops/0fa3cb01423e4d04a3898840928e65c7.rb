class Raindrops
  def self.convert(number)
    hash = { "3" => "Pling", "5" => "Plang", "7" => "Plong" }
    output = ""
    hash.keys.each do |divisor|
      output << hash[divisor] if number % divisor.to_i == 0
    end
    output.empty? ? number.to_s : output
  end
end
