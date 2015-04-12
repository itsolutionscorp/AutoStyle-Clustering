class Phrase

def initialize(str)
	@str = str
end

def word_count
	wordcount = Hash.new(0);
#	words = @str.downcase.tr(",", " ").gsub(/[^0-9a-z ']/i, "").split(" ")
	words = @str.downcase.scan(/[\w']+/)
	words.each { |i| wordcount[i] += 1 }
	wordcount
end

end