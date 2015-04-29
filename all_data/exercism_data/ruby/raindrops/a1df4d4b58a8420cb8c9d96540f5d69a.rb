class Raindrops
  MAP = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }
  def self.convert n
    out = MAP.keys.map do |k|
      n % k == 0 ? MAP[k] : ""
    end.inject("", :+)
    out.empty? ? n.to_s : out
  end
end
