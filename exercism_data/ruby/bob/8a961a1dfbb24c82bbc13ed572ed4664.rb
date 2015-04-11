class Bob
  def hey(sentence)
    case sentence
    when /\A\s*\z/
      'Fine. Be that way!'
    when /\A[^a-z]*[A-Z][^a-z]*\z/
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
