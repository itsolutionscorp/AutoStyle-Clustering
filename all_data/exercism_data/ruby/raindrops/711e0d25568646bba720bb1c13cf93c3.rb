class Raindrops

	SOUNDS = {
		"Pling" => 3,
		"Plang" => 5,
		"Plong" => 7
	}

	def self.convert(count)
		string = String.new
		SOUNDS.each do |sound,value|
			string << sound if count % value == 0 
		end
		string.length < 1 ? string << count.to_s : string
	end
end
