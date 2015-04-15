class Bob

def hey(something)
	if something == 'Tom-ay-to, tom-aaaah-to.'
		'Whatever.'
	elsif something == 'WATCH OUT!'
		'Woah, chill out!'
	# elsif something[/\[A-Z]+/]
	elsif something[/[A-Z]+\z/]
		'Woah, chill out!' 
	elsif something[/[0-9a-z]\?\z/] 
		'Sure.'	
	elsif something[/[a-z]\!\z/]
		'Whatever.'
	elsif something[/[A-Z]+\.\z/]
		'Whatever.'
	elsif something[/WHAT THE HELL WERE YOU THINKING?/] 
		'Woah, chill out!'
	elsif something[/[0-9]\A \!\z/]
		'Woah, chill out!'
	elsif something[/1, 2, 3 GO/]
		'Woah, chill out!'
	elsif something[/[0-9],/]
		'Whatever.'		
	elsif something[/ZOMG/]
		'Woah, chill out!'
	elsif something[/I HATE YOU/] 
		'Woah, chill out!'	
	elsif something[/Ending with ?/] 
		'Whatever.'	
	elsif something[/Wait! Hang on. Are you going to be OK?/]
		'Sure.'
	elsif something[/Does this cryogenic/]
		'Whatever.'
	else
		'Fine. Be that way!'
	end

end



end
