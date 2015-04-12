class Phrase

def initialize(str)
	@str = str
end

def word_count
	words = @str.downcase.scan(/[\w']+/)
	words.each_with_object(Hash.new(0)) { |i, a| a[i] +=1 }
end

end
