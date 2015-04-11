class Raindrops
  FACTOR_SOUNDS = { 3 => "Pling", 5 => "Plang", 7 => "Plong" }

  def self.convert n
  	symphony = ""
  	result = FACTOR_SOUNDS.each do |factor, sound|
  	  if n % factor == 0
  	    symphony += sound
  	  end
  	end
  	symphony == "" ? n.to_s : symphony
  end

end
