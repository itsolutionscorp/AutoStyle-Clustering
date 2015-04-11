class Bob
  
  def hey(input)
    last_char = input[-1, 1]
   
    # new line entires 
    if input =~ /\n/ 
      return 'Whatever.'

    # empty response
    elsif input == '' or input =~ /^\s*$/ then
      return 'Fine. Be that way!'
    
    # only numbers, but handle question
    elsif input !~ /[A-Za-z]/ then  
      if last_char == "?"
        return 'Sure.'
      else
        return 'Whatever.'
      end

    # all upper case response and handle numbers before this
    elsif input == input.upcase then
      return 'Woah, chill out!'

    # questions
    elsif last_char == "?" then
      return 'Sure.'
    
    # catch all for whatevers
    else
      return 'Whatever.'
      end
  end
end
