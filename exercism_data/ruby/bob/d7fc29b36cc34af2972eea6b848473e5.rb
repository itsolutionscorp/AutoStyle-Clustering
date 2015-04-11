class Bob
  # This is the hash of responses that the Bob class can give.
  def self.responses
    { angry: 'Woah, chill out!', inquisitive: 'Sure.',
      taciturn: 'Fine. Be that way!', indifferent: 'Whatever.' }
  end

  def hey(text)
    if angry?(text)
      self.class.responses[:angry]
    elsif inquisitive?(text)
      self.class.responses[:inquisitive]
    elsif taciturn?(text)
      self.class.responses[:taciturn]
    else
      self.class.responses[:indifferent]
    end
  end

  private

  def angry?(text)
    text.upcase == text && text.gsub(/[a-zA-Z]/, '') != text
  end

  def inquisitive?(text)
    text[-1] == '?'
  end

  def taciturn?(text)
    text.gsub(/[\s)(]/,'').empty?
  end
end
