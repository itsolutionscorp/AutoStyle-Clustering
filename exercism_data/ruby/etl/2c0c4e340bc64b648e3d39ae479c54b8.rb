class ETL
	def self.transform (scores)
		transformedScores = Hash.new()
		scores.each {|key , value| value.each{ |val| transformedScores[val.downcase] = key}}
        return transformedScores
	end
end
