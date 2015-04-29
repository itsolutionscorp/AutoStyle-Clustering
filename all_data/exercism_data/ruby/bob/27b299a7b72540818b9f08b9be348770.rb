class Bob
  def hey(sentence)
    if sentence.strip.empty?
      return 'Fine. Be that way!'
    else if sentence.upcase == sentence and sentence.upcase != sentence.downcase
      return 'Woah, chill out!'
    else if sentence.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
    end
    end
  end
end
