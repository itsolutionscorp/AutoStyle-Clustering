class Raindrops

  def self.convert(n)
    (1..n).reduce(false) do |s, i| 
      (a = [["Pling"][i%3], ["Plang"][i%5], ["Plong"][i%7]].join).empty? ? "#{i}" : a
    end
  end

end
