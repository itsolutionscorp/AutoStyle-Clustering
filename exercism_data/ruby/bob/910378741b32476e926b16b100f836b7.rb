class Bob
	def self.talk_to(msg = '')
		if msg == ''
			return 'Fine, Be That Way'
		elsif msg.include? '?'
			return 'Sure.'
		elsif msg == msg.upcase
			return 'Whoah, Chill Out!'
		elsif !msg.include? '?'
			return 'Whatever'
		end
	end
end

#We are going to 
#refactor this into a teenager class
