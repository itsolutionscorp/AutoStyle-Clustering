class Raindrops
  def self.convert(drops)
    ps = ""
    ps << "Pling" if drops % 3 == 0
    ps << "Plang" if drops % 5 == 0
    ps << "Plong" if drops % 7 == 0
    ps.length == 0 ? drops.to_s : ps
  end
end
