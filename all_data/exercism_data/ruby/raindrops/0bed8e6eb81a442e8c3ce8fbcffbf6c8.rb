class Raindrops
  def self.convert(n)
    hash_drops = { "Pling" => 3, "Plang" => 5, "Plong" => 7 }
    ret = ""
    hash_drops.each do |drop, divisor|
      ret+=drop if n % divisor == 0
    end
    return n.to_s if ret.empty?
    ret
  end
end
