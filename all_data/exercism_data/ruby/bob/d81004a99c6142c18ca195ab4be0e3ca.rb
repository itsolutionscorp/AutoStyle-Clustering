class Bob
  def hey(sentence)
    sentence = sentence.to_s.strip

    case sentence
    when ''
      'Fine. Be that way!'
    when sentence.upcase
      'Woah, chill out!'
    when ->(s) { s.end_with? '?' }
      'Sure.'
    else
      'Whatever.'
    end
  end
end
