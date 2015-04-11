require 'mathn'

class Raindrops

  def self.convert(num)
    arr = num.prime_division.flatten.reduce("") { |res, arr|
      res << "Pling" if arr == 3
      res << "Plang" if arr == 5
      res << "Plong" if arr == 7
      res
    }
    arr.empty? ? num.to_s : arr
  end

end
