class Bob
	def hey (string)



    return 'Woah, chill out!' if string.match(/[a-zA-Z]/) && string == string.upcase
		
    return 'Sure.' if string.end_with?("?")
    
    return 'Fine. Be that way!' if string.strip.empty?

    return 'see ya' if ['exit', 'bye', 'im leaving'].include? string

    return 'help: talk to me me by typeing and pressing enter. type exit to go' if string.downcase == 'help'

		"Whatever."
	end
end
puts 'wassap'
string = gets.chomp
puts Bob.new.hey string

until ['exit', 'bye', 'im leaving'].include? string do
  string = gets.chomp
  puts Bob.new.hey string

end
