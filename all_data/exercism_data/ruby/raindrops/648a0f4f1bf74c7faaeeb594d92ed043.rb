class Raindrops
	def self.convert(no)
		speak, ref = "", {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
		ref.each { |prim, rainspeak| speak.concat(rainspeak) if ( no.modulo(prim) == 0 ) }
		return speak.concat(no.to_s) if ( speak == "" )
		speak
	end

end
