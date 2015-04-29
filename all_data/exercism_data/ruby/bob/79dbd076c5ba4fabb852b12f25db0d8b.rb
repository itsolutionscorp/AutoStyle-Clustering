# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Woah, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying anything.

# He answers 'Whatever.' to anything else

class Responder

  def respond_to(prompt)
    @prompt = prompt
    if shouting?
      "Woah, chill out!" 
    elsif asking?
      "Sure."
    elsif silent?
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end

  private

  def shouting? 
    @prompt == @prompt.upcase && (@prompt =~ /[a-zA-Z]+/) != nil
  end

  def asking?  
    @prompt[-1] == "?"
  end

  def silent? 
    @prompt.strip == ""
  end
end

class Bob
  DEFAULT_RESPONDER_CLASS = Responder

  def initialize(responder_class = DEFAULT_RESPONDER_CLASS)
    @responder = responder_class.new
  end

  def hey prompt
    @responder.respond_to(prompt)
  end
end
