class Raindrops

  def self.convert(number)
    status = []
    output =  { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

    (3..7).step(2).map { |i| number % i == 0 ? status << output[i] : status }
    status.empty? ? number.to_s : status.join
  end
end