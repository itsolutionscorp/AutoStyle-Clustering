class Anagram

def initialize(needle)
        @needle = needle.downcase
	@needlets = Hash.new(0)
	@needle.each_char {|c| @needlets[c]+=1 }
end

def match(haystack)
	answer = []
	haystack.each do |hay|
		next if hay.downcase == @needle		# Same word is not an anagram
		next unless hay.length == @needle.length
		haylets = Hash.new(0)
		hay.each_char {|c| haylets[c.downcase]+=1 }
		answer << hay if @needlets == haylets
	end
	answer
end

end
