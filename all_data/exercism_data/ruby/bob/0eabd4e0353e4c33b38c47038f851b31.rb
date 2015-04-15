  class Bob
  	def hey(conversation)
    	conversation = conversation.gsub(/[^0-9A-Za-z?!]/, '')
    	if conversation.empty?
      	"Fine. Be that way!"
    	elsif(!conversation.match(/\p{Lower}/) &&  !(conversation.to_s =~ /^[-+]?[0-9]+[?]?$/))
     	 "Woah, chill out!"
   		elsif conversation.end_with?('?')
        "Sure."
    	else
      	"Whatever."
    	end
  	end
end
