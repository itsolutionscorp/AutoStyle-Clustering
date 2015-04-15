class ETL
	def self.transform(word)
    trans = {}

    word.keys.each do |key| 

      word[key].each do |val|
        trans[val.downcase] = key
      end

    end

    return trans
	end
end
