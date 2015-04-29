#params [Number]
#return [String]

class Raindrops
  def self.convert(n)
    resulting_string = ''
    sounds = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

    sounds.each_with_object("") { |(k, v)|
      resulting_string += v if n % k == 0
    }

    resulting_string.empty? ? "#{n}" : resulting_string
  end

end
