class Raindrops

  def self.convert(number)
    status = []
    output =  { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

    (3..7).step(2) { |i| status << output[i] if number % i == 0 }
    status.empty? ? number.to_s : status.join
  end
end
