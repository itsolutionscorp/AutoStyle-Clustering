# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Woah, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying anything.

# He answers 'Whatever.' to anything else

class Bob
  def hey prompt
    response = Response.new(prompt)
    response.set_response
    response.response 
  end
end

class Response
  attr_reader :response

  def initialize(prompt)
    @prompt = prompt
    @response = "Whatever."
  end

  def set_response
    if shouting?
      @response = "Woah, chill out!" 
    elsif asking?
      @response = "Sure."
    elsif silent?
      @response = "Fine. Be that way!"
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
