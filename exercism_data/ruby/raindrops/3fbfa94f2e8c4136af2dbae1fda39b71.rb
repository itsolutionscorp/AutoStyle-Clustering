class Raindrops


	def self.convert(n)
		sound = ""
		raindrops = { 3 => "Pling", 5 => "Plang", 7=> "Plong" }
		raindrops.each do |k,v|
			if n % k == 0; sound.concat(v)
			end
		end

		sound.eql?("") ? sound = n.to_s : sound

	end
end
