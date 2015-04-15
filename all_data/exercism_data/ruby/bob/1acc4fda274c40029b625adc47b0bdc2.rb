class Bob

	#attr_accessor :names

	
	#@-> instance variable
	def initialize()
		#@names = name
	end
	
	def hey (msg)
	
		if msg == ''
			puts "Fine. Be that way!"
		elsif msg == msg.upcase
			puts "Woah, chill out!"
		#elsif msg.include? "?" 
		elsif msg.end_with?("?")
			puts "Sure."
		else
			puts "Whatever."
		end
	end
end

#if __FILE__ == $0
#  teenager = Bob.new
  
  
 # puts "Input a msg:"
 # msg = gets.chomp
 # teenager.hey("#{msg}")

 # Change name to be "Bob"
 #teenager.names = "Bob"
 #teenager.hey
  

#end
