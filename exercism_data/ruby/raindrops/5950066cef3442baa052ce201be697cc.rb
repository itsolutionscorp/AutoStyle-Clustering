require 'prime'
class Raindrops

  def self.convert num
    prime_factor = Prime.prime_division(num).flatten
    ret = ''
    {3 => "Pling", 5 => "Plang", 7 => "Plong"}.each do |i, s|
      ret << s if prime_factor.include?(i)
    end
    ret.empty? ? num.to_s : ret
  end

end
