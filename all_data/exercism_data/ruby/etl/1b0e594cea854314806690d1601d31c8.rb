class ETL
	def self.transform(old)
		Hash[*old.map{|score, words| words.map {|w| [w.downcase, score]} }.flatten]
	end
end
