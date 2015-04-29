class Bob
  
  def whatever(input)
    last_char = input.split('').pop
    if input =~ /\n/
      return 'Whatever.'
    
    elsif input !~ /[A-Za-z]|/ then
      if last_char !~ /\?/ or not input == '' or not input =~ /^\s*$/ 
        return 'Whatever.'
      end
    elsif input =~ /\d/ and input !~ /[A-Za-z]/ then
      if last_char !~ /\?/
        return 'Whatever.'
      end
    else 
      return nil
    end
  end

  def fine(input)
    if input == '' or input =~ /^\s*$/ then
      return 'Fine. Be that way!'
    else 
      return nil
    end
  end

  def sure(input)
    last_char = input.split('').pop
    if last_char == "?" and whoa(input).nil? 
      return 'Sure.'
    elsif input =~ /\d/ and input !~ /[A-Za-z]/ then
      if last_char == "?"
        return 'Sure.'
      end 
    else 
      return nil
    end
  end

  def whoa(input)
    if input == input.upcase
      return 'Woah, chill out!'
    else 
      return nil
    end
  end

  def hey(input)
    potential_responses = [whatever(input), fine(input), sure(input), whoa(input)]
    potential_responses.each do |x| 
      unless x.nil?
        return x
      end
    end
    if potential_responses.all? {|element| element == nil}
      return 'Whatever.'
    end 
  end
end 
