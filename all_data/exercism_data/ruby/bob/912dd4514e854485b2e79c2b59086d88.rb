class Bob

  def hey(content)
    content ||= ''
    respond_to(content)
  end


  private

  def respond_to(content)
    %w(silence shout question).each do |type|
      return self.send("respond_to_#{type}") if self.send("#{type}?", content)
    end
    respond_to_statement
  end

  def question?(content)
    content.end_with?('?')
  end

  def shout?(content)
    content.upcase == content
  end

  def silence?(content)
    content.empty?
  end

  def respond_to_shout
    'Woah, chill out!'
  end

  def respond_to_question
    'Sure.'
  end

  def respond_to_silence
    'Fine. Be that way.'
  end

  def respond_to_statement
    'Whatever.'
  end
end
