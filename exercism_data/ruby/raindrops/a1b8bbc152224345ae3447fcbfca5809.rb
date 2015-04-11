class Fixnum
  def has_prime_factor?(factor)
    self % factor == 0
  end
end

class Raindrops
  def self.convert(num)
    drops = [
              { :factor => 3, :sound => "Pling" },
              { :factor => 5, :sound => "Plang" },
              { :factor => 7, :sound => "Plong" }
            ]
    sound = ""
    drops.each { |drop| sound += drop[:sound] if num.has_prime_factor? drop[:factor] }
    !sound.empty? ? sound : num.to_s
  end
end
