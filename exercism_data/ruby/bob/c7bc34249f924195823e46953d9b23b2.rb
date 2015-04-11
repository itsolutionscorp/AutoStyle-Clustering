class Bob

  def hey(input)
    if input.gsub(" ", "") == (" ")
     response_to_silence
   elsif input == input.upcase 
    response_to_shouting
  elsif input.end_with? ("?")
    response_to_question
  else 
    "whatever"
  end
 end
end


def response_to_silence
  puts "Fine. Be that way!"
end

def response_to_shouting
  puts "Woah, chill out!"
end

def response_to_question
  puts "Sure."
end

def response_to_forceful_question
  puts 
end

end
