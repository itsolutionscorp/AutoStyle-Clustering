class Raindrops

  def self.convert(i)
    (drop = [["Pling"][i % 3], ["Plang"][i % 5], ["Plong"][i % 7]].compact.join).empty? ? i.to_s : drop
  end
end
