class Raindrops

  def self.convert(num)
    num_string = ''
    rain_hash = { 3 => "Pling", 5 => "Plang", 7 => "Plong"}

    rain_hash.each { |k,v| num_string += v if (num % k).zero? }
    num_string.empty? ? (return num.to_s) : (return num_string)
  end
end
