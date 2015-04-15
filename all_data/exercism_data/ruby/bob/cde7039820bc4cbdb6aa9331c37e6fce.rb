class Bob

  EMPTY = /^$/
  ALL_SPACES = /^\s+$/
  ALL_CAPS = /^[^a-z]+$/
  QUESTION = /\?$/

  def hey(words)
    words = words.gsub(/\n/, " ")
    case 
    when (words.match(EMPTY) || words.match(ALL_SPACES))
      return 'Fine. Be that way!' 
    when words.match(ALL_CAPS)
      return 'Woah, chill out!'
    when words.match(QUESTION)
      return 'Sure.'
    else 
      return 'Whatever.'
    end 
  end

end
