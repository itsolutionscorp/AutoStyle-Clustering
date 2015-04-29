class Bob

  #
  # not totally psyched about this implementation
  # it seems like it could be more readable
  #

  def hey(words)
    words = words.gsub(/\n/, " ")
    case 
    when (words.match(/^$/) || words.match(/^\s+$/))
      return 'Fine. Be that way!' 
    when words.match(/^[^a-z]+$/)
      return 'Woah, chill out!'
    when words.match(/\?$/)
      return 'Sure.'
    else 
      return 'Whatever.'
    end 
  end

end
