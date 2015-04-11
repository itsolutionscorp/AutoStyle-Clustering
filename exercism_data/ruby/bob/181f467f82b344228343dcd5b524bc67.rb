class Bob

  def hey text
    @text = text

    ANSWERS.find { |kind, answer| send :"#{ kind }?" }.last
  end

private

  attr_reader :text

  ANSWERS = {
    :silence  => 'Fine. Be that way!',
    :yell     => 'Woah, chill out!',
    :question => 'Sure.',
    :default  => 'Whatever.'
  }

  def silence?
    text.strip.empty?
  end

  def yell?
    text == text.upcase and text != text.downcase
  end

  def question?
    text =~ /\?\z/
  end

  def default?
    true
  end

end
