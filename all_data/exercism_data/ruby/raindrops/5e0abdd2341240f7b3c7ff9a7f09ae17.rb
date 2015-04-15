class Raindrops
	
	PPP_HASH = {"Pling" => 3, "Plang" => 5, "Plong" => 7}

  def self.convert(num)

    return num.to_s unless PPP_HASH.keys.any? {|key| num % PPP_HASH[key] == 0}
    PPP_HASH.keys.select {|key| num % PPP_HASH[key] == 0}.join

  end

end
