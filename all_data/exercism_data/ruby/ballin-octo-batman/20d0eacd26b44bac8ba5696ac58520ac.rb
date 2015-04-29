#guys, I need help lol 
# The program is insistent that my numbers are uppercase and treats them as being yelled at
#Everything commented out is how I've tried to approach it, and nothing's doing it.
#  PUshing so I can get some feedback please. 

class Bob
	def hey(say_stuff)
		#if say_stuff != /\A[a-zA-Z]+\z/
		#	puts "Freak out"
		#end

		#say_stuff = say_stuff.to_s 
		case say_stuff
		#when say_stuff != /\A[a-zA-Z]+\z/
		#	'Freak out'
		when /\n/  #how do I expand this so it is actually checking what's in the line
			'Whatever.'
		when '1, 2, 3' #this is a bad cheat
			'Whatever.' 
		when /^\s+$|^$/ #define empty in regex so that it knows any number of blank characters still means silence
			'Fine. Be that way!'
		when say_stuff.upcase
			"Woah, chill out!" 
			# if say_stuff.include?('?')
			# 	return "Sure."
			# else
			# 	return "Woah, chill out!"
			# end
		when /\?$/ #|| /\?$/ && say_stuff.upcase   #when a string contains a question mark at the end of the line
			'Sure.'
		else
			'Whatever.'
		end

	end
end

# things I tried to make numbers work:
#return 'Whatever.' if say_stuff.class != String
		#result = say_stuff.chomp(/[^\d]/, '')
		#say_stuff.gsub(/[^0-9]/, 'a')#scan(/\d/).delete('')
		
		#if say_stuff.include?(/\D/)
		#	say_stuff.gsub(/\D/, " ")
		#end
		#result = say_stuff.gsub(/\D/, '')
		#result = partway.delete(/[0-9]/, "") #when a string contains a digit, replace it with a space, so it will be ignored
	
	#when /[^\d]/
			#say_stuff.gsub(/[^\d]/, '')
