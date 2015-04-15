class Raindrops
	@@sounds = {
		3 => "Pling",
		5 => "Plang",
		7 => "Plong",
	}
	def self.convert(drop)
		drop_sound = ""
		@@sounds.each do |factor, sound|
			drop_sound += sound if drop%factor == 0
		end
		drop_sound>"" ? drop_sound : drop.to_s
	end
end
