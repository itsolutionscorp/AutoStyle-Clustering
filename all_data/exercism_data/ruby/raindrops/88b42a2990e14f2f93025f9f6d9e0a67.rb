class Raindrops
  def self.convert(number)
    results = []
    drops = [[3, "Pling"], [5, "Plang"], [7, "Plong"]]

    drops.each do |drop|
      results << drop[1] if number % drop[0] == 0
    end

    results.length == 0 ? number.to_s : results.join
  end
end
