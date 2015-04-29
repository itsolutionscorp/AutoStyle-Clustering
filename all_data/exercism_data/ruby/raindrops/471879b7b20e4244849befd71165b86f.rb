class Raindrops
	
	PPP_HASH = {"Pling" => 3, "Plang" => 5, "Plong" => 7}
	PPP_ARR = ["Pling", "Plang", "Plong"]

  def self.convert(num)

    return num.to_s unless PPP_ARR.any? {|key| num % PPP_HASH[key] == 0}
    PPP_ARR.select {|key| num % PPP_HASH[key] == 0}.join

  end

end
