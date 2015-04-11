class Answerer
  def reponses
    {}
  end

  def hey(sentence)
    answer Talker.new(sentence)
  end

  protected

  def answer(talker)
    reponses.each do |action, response|
      return response if talker.send action
    end
  end
end

class Roger < Answerer
  def reponses
    {
      :silent?    => "Are you okay?",
      :screaming? => "Please, calm down.",
      :asking?    => "Sorry, I can't help you.",
      :talking?   => "Thats interesting!"
    }
  end
end

class Bob < Answerer
  def reponses
    {
      :silent?    => "Fine. Be that way!",
      :screaming? => "Woah, chill out!",
      :asking?    => "Sure.",
      :talking?   => "Whatever."
    }
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
