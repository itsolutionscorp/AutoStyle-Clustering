class Raindrops

	def self.convert(count)
		string = String.new
		string << "Pling" if count % 3 == 0
		string << "Plang" if count % 5 == 0
		string << "Plong" if count % 7 == 0
		string << count.to_s if string.length < 1
		string
	end
end
