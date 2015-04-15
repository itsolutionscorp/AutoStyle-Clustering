#!/usr/bin/env ruby
#Bob exercism assignment

class Bob
	def hey(statement)
		#check first if we're being rude to Bob (shouting)
		if statement == statement.upcase and not statement.empty?
			return "Woah, chill out!"
		#next see if we're asking a question
		elsif statement.end_with?("?")
			return "Sure."
		#if we're not being rude or asking a question, check if we're saying anything
		elsif statement.empty?
			return "Fine. Be that way!"
		#if we are saying something that's not rude, a question or 'nothing', default to apathethic Bob
		else
			return "Whatever."
		end
	end
end
