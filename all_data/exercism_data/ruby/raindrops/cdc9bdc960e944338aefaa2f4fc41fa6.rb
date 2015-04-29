class Raindrops
  def self.convert(num)
    pling = "Pling" if num % 3 == 0
    plang = "Plang" if num % 5 == 0
    plong = "Plong" if num % 7 == 0
    drops = [pling, plang, plong].join
    drops.empty? ? num.to_s : drops
  end
end
