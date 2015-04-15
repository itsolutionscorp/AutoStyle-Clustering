class Phrase
	def initialize test_word
		@test_word = test_word
	end
	def word_count
		@test_word.downcase.split(/\s*\W\s*/).group_by{|x|x}.inject({}) {|h,(k,v)| h[k] = v.count;h}
	end
end
