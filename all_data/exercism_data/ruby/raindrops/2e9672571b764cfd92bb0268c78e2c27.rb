class Raindrops
  def self.convert(num)
    drop = ""
    drop += "Pling" if num % 3 == 0
    drop += "Plang" if num % 5 == 0
    drop += "Plong" if num % 7 == 0
    return drop unless drop == ""
    num.to_s
  end
end
