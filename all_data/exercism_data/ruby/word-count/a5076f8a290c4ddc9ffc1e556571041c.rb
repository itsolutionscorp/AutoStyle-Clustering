class Phrase
	def self.new(string)
		array = string.split
		hash = array.group_by {|x| x }
		count = hash.inject({}){ |hash, (k, v)| hash.merge( k => v.length )  }
		@word_count = count
	end

	def word_count
		count 
	end
end
