class Bob 

 def hey(input)
    return 'Woah, chill out!' if input.upcase == input && input =~ /[A-Z]+/
    return 'Sure.' if input.slice(-1) == '?'
    return 'Fine. Be that way!' if input.empty? || input.gsub(/\s/, '').empty?
   'Whatever.'
 end

end
