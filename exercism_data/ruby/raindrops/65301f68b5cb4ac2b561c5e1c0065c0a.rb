class Raindrops
  def self.convert(i)
    (raindrop = [["Pling"][i%3],["Plang"][i%5],["Plong"][i%7]].join).empty? ? i.to_s : raindrop
  end
end
