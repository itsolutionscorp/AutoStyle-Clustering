class Bob
  
  def hey( say )
    return 'Fine. Be that way.' if say.nil? || say.empty?
    return 'Woah, chill out!' unless /[[:lower:]]/.match(say)
    return 'Sure.' if say[say.length-1] == "?"
    return "Whatever." unless say.nil? 
  end  
  
end  
