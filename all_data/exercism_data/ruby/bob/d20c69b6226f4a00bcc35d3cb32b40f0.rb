class Bob
  RESPONSES = {
    :silent?    => "Fine. Be that way!",
    :screaming? => "Woah, chill out!",
    :asking?    => "Sure.",
    :talking?   => "Whatever."
  }

  def hey(sentence)
    respond_to Person.new(sentence)
  end

  private

  def respond_to(person)
    RESPONSES.each do |action, response|
      return response if person.send action
    end
  end
end

class Person
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
