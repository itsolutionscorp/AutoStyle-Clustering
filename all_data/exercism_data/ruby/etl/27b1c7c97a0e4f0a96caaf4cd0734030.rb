class ETL
	def ETL.transform (scoreLetterMap)
		letterScoreMap = Hash.new
		scoreKeys = scoreLetterMap.keys
		scoreLetterMap.each_key {|score|
			letterArray = scoreLetterMap[score]
			letterArray.each {|letter|
				letterScoreMap.store(letter.downcase, score)
			}
		}
		return letterScoreMap	
	end
end
