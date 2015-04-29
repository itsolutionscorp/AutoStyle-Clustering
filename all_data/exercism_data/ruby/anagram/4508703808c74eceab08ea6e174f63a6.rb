class Anagram
	def initialize(word)
		@w = word.split('').sort
	end
	def match(options)
		matched_options = []
		options.each do |x|
			matched_options << x if x.split('').sort == @w
		end
		return matched_options.sort
	end
end
