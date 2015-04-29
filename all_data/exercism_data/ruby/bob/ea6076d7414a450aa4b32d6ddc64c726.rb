class Bob
  # This is the hash of responses that the Bob class can give.
  def self.responses
    { angry: 'Woah, chill out!', inquisitive: 'Sure.',
      taciturn: 'Fine. Be that way!', indifferent: 'Whatever.' }
  end

  def hey(text)
    self.class.responses[emotion(text)]
  end

  # Determines how Bob interprets a message
  # Returns a symbol of the emotion he feels the text is
  def emotion(text)
    if text.upcase == text && text.gsub(/[a-zA-Z]/, '') != text
      :angry
    elsif text[-1] == '?'
      :inquisitive
    elsif text.gsub(/[\s)(]/,'').empty?
      :taciturn
    else
      :indifferent
    end
  end
end
