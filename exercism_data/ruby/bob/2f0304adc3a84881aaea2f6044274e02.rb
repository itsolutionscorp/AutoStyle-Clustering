class Bob
  RESPONSES = {
    :silent?    => "Fine. Be that way!",
    :screaming? => "Woah, chill out!",
    :asking?    => "Sure.",
    :talking?   => "Whatever."
  }

  def hey(sentence)
    answer Talker.new(sentence)
  end

  private

  def answer(talker)
    RESPONSES.each do |action, response|
      return response if talker.send action
    end
  end
end

class Talker
  def initialize(sentence)
    @sentence = sentence
  end

  def silent?
    @sentence.nil? or @sentence.empty?
  end

  def screaming?
    @sentence == @sentence.upcase
  end

  def asking?
    @sentence.end_with? "?"
  end

  def talking?
    true
  end
end
