require 'mathn'

class Raindrops

  def self.convert(num)
    return num.to_s if num == 1
    arr = num.prime_division.flatten
    arr.reduce("") { |res|
      res << "Pling" if arr.include?(3)
      res << "Plang" if arr.include?(5)
      res << "Plong" if arr.include?(7)
      res << num.to_s if res == ""
      return res
    }
  end

end
