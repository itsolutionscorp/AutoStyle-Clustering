class Bob

  def hey(noise)
    # choose response based on classification of input
    case classify(noise)
    when :yelling
      "Woah, chill out!"
    when :query
      "Sure."
    when :silence
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def classify(noise) #Bob's noise classification criteria
    #Priorities are implicit in ordering...
    return :silence if noise.strip.length == 0
    return :yelling if noise == noise.upcase 
    return :query if noise.end_with?('?')  
    return :whateva
  end

end
