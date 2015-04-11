class Bob
  
  def hey (string)
    return 'Fine. Be that way!' if string.scan(/\w/).empty? 
    string.gsub!(/\d/, "")
    return 'Whoa, chill out!' if string.index(/\w/) && !(string.upcase!)
    return 'Sure.' if string[-1] == "?"
    'Whatever.'
  end
end
