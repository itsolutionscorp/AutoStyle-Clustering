class Phrase
	def initialize(string)
		array = string.downcase.scan(/[\w']+/)
		hash = array.group_by {|x| x }
		@count = hash.inject({}){ |hash, (k, v)| hash.merge( k => v.length )  }
	end
	def word_count
		@count
	end
end
