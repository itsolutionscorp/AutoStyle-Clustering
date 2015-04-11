class Raindrops
  @raindrop_hash = Hash[3, "Pling", 5, "Plang", 7, "Plong"]

  def self.convert ( number )
    drops = collect_raindrops( number )
    drops.empty? ? number.to_s : drops
  end

  def self.collect_raindrops( number )
    @raindrop_hash.collect do |k,v|
      v if number % k == 0
    end.join
  end
end
