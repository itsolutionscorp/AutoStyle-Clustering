class Raindrops
  PRIME_DROPS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  
  def self.convert(number)
    output = PRIME_DROPS.each_with_object("") do |(factor, drop), s|
      s << drop if number.modulo(factor).zero?
    end

    output.empty? ? number.to_s : output
  end
end
