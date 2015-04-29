class Raindrops
  @@raindrops = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(number)
    conversion = @@raindrops.keys.inject("") do |sentence, prime|
      number % prime == 0 ? sentence += @@raindrops[prime] : sentence
    end

    conversion.empty? ? number.to_s : conversion
  end
end
