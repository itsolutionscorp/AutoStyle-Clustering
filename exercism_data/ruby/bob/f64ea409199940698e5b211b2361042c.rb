class Bob
  
  def fine(input)
    unless input =~ /^\n/
      if input == '' or input =~ /^\s*$/ then
        return 'Fine. Be that way!'
      end
    end
  end

  def sure(input)
    sure = nil
    last_char = input.split('').pop
    
    if last_char == "?" and whoa(input).nil? 
      sure = 'Sure.'
    elsif input =~ /\d/ and input !~ /[A-Za-z]/ then
      if last_char == "?"
        sure = 'Sure.'
      end 
    end

    return sure
  end

  def whoa(input)
    if input == input.upcase 
      unless input !~ /[a-zA-Z]/
        return 'Woah, chill out!'
      end
    end
  end

  def hey(input)
    respond_with = nil 
    potential_responses = [fine(input), sure(input), whoa(input)]

    potential_responses.each do |response| 
      unless response.nil?
        respond_with = response
      end
    end
    if potential_responses.all? {|element| element == nil}
      respond_with = 'Whatever.'
    end

  return respond_with
  end
end 
