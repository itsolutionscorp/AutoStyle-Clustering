class Raindrops
  RAINDROPS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong" }
  def self.convert(n)
    forecast = RAINDROPS.reduce("") do |r,(k,v)|
      n%k.zero? ? r << v : r
    end
    forecast.empty? ? n.to_s : forecast
  end
end # end class Raindrops
