class Raindrops
	def self.convert(num)
		conversions = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    drops = ""
    conversions.each {|k, v| drops += v if num.divisible_by(k)}
    drops.empty? ? num.to_s : drops
  end
end

class Fixnum
	def divisible_by(num)
		self % num == 0
	end
end
