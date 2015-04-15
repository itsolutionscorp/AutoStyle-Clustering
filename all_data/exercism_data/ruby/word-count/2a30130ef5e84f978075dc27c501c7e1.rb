class Phrase
	def initialize(string)
		@string = string
	end

	def word_count
		array = @string.downcase.scan(/[\w']+/)
		hash = array.group_by {|x| x}
		count = hash.inject({}){ |h, (k,v)| h.merge( k => v.length ) }
	end
end
