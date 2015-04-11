class Bob

  def initialize
    @my_responses = [ Response.to(:shout, 'Woah, chill out!'),
                      Response.to(:question, 'Sure.'),
                      Response.to(:mute, 'Fine. Be that way!'),
                      Response.to(:anything, 'Whatever.')]
  end

  def hey(text)
    phrase = Phrase.new(text)
    @my_responses.find {|r| r.responds? phrase } .text
  end
end

class Response

  class << self
    alias_method :to, :new
  end
  attr_reader :text

  def initialize(phrase_type, response_text)
    @predicate = "#{phrase_type}?"
    @text = response_text
  end

  def responds?(phrase)
    phrase.public_send(@predicate)
  end
end

class Phrase
  attr_reader :text

  def initialize(text)
    @text = String(text).strip
  end
  def shout?
    all_caps?(text)
  end
  def question?
    text.end_with?('?')
  end
  def mute?
    text.empty?
  end
  def anything?
    true
  end
  def all_caps?(text)
     !text.empty? && text !~ /[a-z]/
  end

end
