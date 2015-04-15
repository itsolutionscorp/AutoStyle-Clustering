class Raindrops
  def self.convert(number)
    translation = {3=>"Pling", 5=>"Plang", 7=>"Plong"}
    raindrop_speak = translation.collect { |prime, word| word if number % prime == 0 }.join

    raindrop_speak.empty? ? number.to_s : raindrop_speak
  end
end
