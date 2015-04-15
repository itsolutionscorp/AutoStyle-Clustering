class Raindrops	
	def self.convert(n)
		output = ""

		factor = [3, 5, 7].select { |f| self.is_factor?(n, f)}
		
		factor.each { |r| output << { 3 => "Pling", 5 => "Plang", 7 => "Plong" }[r]}
		output << n.to_s if factor.empty?
		output
	end

	def self.is_factor?(n, f)
		n % f == 0
	end
end
