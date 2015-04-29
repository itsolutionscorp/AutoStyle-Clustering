class Raindrops
	def self.convert(num)
		sound = ""
		conversions = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    conversions.each {|k, v| sound += v if num.divisible_by(k)}
    sound.empty? ? num.to_s : sound
  end
end

class Fixnum
	def divisible_by(num)
		self % num == 0
	end
end
