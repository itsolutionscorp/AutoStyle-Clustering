class Raindrops
    DROPS = {"3" => "Pling", "5" => "Plang", "7" => "Plong"}
    def self.convert(n) 
    	out = DROPS.each.map {|key, val| n%key.to_i==0 ? val : nil}.join
    	out.empty? ? n.to_s : out
    end
end
