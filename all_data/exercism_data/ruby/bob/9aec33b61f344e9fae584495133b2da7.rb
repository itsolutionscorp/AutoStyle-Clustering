class Bob 

def hey(something)
	if shouting?(something)
		"Woah, chill out!"
	elsif question?(something)
		"Sure."
	elsif silent?(something)
		"Fine, be that way. "	
	else
		"Whatever."
	end
end

def question?(sentence)
	sentence.end_with?("?")
end
	
def silent?(sentence)
	sentence.empty?
end

def shouting?(sentence)
	sentence.upcase == sentence	
end

end
