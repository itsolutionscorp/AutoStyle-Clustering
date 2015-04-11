class Array
	def keep
		if block_given?
			keeping = []
			self.each do |element|
				return_from_block = yield(element)
				if return_from_block 
					keeping << element
				end
			end
		else
			raise ArgumentError.new('No block given.')
		end
		keeping
	end
	
	def discard
		if block_given?
			discarding = []
			self.each do |element|
				return_from_block = yield(element)
				unless return_from_block 
					discarding << element
				end
			end
		else
			raise ArgumentError.new('No block given.')
		end
		discarding
	end
end
