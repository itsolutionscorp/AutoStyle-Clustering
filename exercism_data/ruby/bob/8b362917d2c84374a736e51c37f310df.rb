class Bob
  def hey sentence
    case sentence.strip
    when /\A[^a-zA-Z]*[A-Z][^a-z]*\Z/
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    when /\A\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
