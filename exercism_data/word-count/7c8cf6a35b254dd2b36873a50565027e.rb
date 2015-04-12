class Phrase

def initialize(str)
	@str = str
end

def word_count
	wordcount = Hash.new;
	words = @str.downcase.tr(",", " ").gsub(/[^0-9a-z ']/i, "").split(" ")
	words.each { |i| wordcount[i] = wordcount[i].to_i.succ }
	wordcount
end

end
