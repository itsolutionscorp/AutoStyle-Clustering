class Raindrops
  def self.convert(n)
    string = ""
    rain = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

    rain.each do |factor, word|
      string += word if n % factor == 0
    end

    string = n.to_s if string == ""

    string
  end
end
