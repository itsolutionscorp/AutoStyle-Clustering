class Anagram 
	attr_accessor :word
	
	def initialize(word)
		@word = word.downcase
	end
	
	def match(vals)
		result = []
		vals.each do |val|
			next unless can_be_anagram?(val,word)
			letters = @word.split ''
			val.split('').each { |letter| break unless letters.delete_one letter.downcase }
			result << val if letters.empty?
		end
		result
	end
	
	private
	def can_be_anagram?(anagram, word)
		!(anagram.downcase == @word || anagram.length != @word.length)
	end
	
end

class Array
        def delete_one(elem)
                index = self.index elem
                self.delete_at index if index
                index
        end
end
