class Scrabble
	def initialize(string)
		@string = string
	end
	def score
		normalize = @string.gsub(/\W+/,"").downcase unless @string == nil
		return 0 if @string == nil || normalize == ""
		
		array = normalize.split(//).map do |x| 
			x.gsub(/[aeioulnrst]/, "1").gsub(/[dg]/,"2").gsub(/[bcmp]/,"3").gsub(/[fhvwy]/, "4").gsub(/[k]/,"5").gsub(/[jx]/,"8").gsub(/[qz]/,"10")
		end
		array.inject(0){|sum,x| sum + x.to_i}
	end
	def self.score(string)
		Scrabble.new(string).score
	end
end
