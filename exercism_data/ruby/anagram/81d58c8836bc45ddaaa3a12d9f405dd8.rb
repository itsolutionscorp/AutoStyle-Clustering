class Anagram

	def initialize(anagram)
		@anagram = anagram
		@sorted_anagram = anagram.downcase.split(//).sort.join
	end

	def match(value)
		value.delete_if {|word| word.downcase == @anagram.downcase }
		value.select { |word| word.downcase.split(//).sort.join.casecmp(@sorted_anagram) == 0 }
	end
end
