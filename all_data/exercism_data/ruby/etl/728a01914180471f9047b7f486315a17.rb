class ETL
	def ETL.transform(oldData)
		# Define a global variable, this variable will contain the HASH of the final result
		@resultHash=Hash.new		
		oldData.keys.each {|k| 		
			valueArray=oldData[k] # The value var contains an ARRAY
			valueArray.each {|letterUpper|				
				@resultHash[letterUpper.downcase] = k				
			}			
		}
		return @resultHash
	end 
end
