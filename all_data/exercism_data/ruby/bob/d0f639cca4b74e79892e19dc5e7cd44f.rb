class Bob
 
  def hey string
    case 
      when string.empty? || string.include?('  ') 
        "Fine. Be that way!"
      when string == string.upcase
        "Woah, chill out!"
      when string.end_with?('?')
        "Sure."
      else 
        "Whatever."
    end	
  end

end
