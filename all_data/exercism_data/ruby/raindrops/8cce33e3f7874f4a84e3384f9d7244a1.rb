class Raindrops
  def self.convert(int)
    factors = (1..int).select { |n| (int % n).zero? }
    factors.keep_if { |f| [3,5,7].include?(f) }
    factors.empty? ? int.to_s : factors.join.gsub(/[357]/, "3" => "Pling", "5" => "Plang", "7" => "Plong" )
  end
end
