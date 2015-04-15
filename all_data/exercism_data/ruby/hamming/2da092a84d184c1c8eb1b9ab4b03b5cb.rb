# Hamming Computer
# Demannu 9/29/2014
class Hamming
	def self.compute(seqone, seqtwo)
		oneSplit = seqone.scan(/./)
		twoSplit = seqtwo.scan(/./)
		count = 0
		errors = 0
		if oneSplit.respond_to?("each")
			oneSplit.each do |oneDNA|				
				if count < seqone.length && count < seqtwo.length
					if oneDNA != twoSplit[count]
						errors = errors + 1		
					end
					count = count + 1					
				end
			end
		end
		return errors
	end
end
