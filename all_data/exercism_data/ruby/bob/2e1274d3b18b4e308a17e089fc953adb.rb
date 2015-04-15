class Bob
  def hey(words)
    if words.strip.empty?
      return 'Fine. Be that way!'
    end

    if words == words.upcase
      return 'Woah, chill out!'
    end

    if words[-1] == '?'
       return 'Sure.'
    end

    'Whatever.'
  end
end
