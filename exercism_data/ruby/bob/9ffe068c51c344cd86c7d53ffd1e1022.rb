class Bob
  def hey(params)
    @conversation = Conversation.new(params)
    respond
  end

  private

    def respond
      return "Fine. Be that way." if @conversation.is_meaningless?
      return "Sure."              if @conversation.is_asking?
      return "Woah, chill out!"   if @conversation.is_shouting?
      return "Whatever."
    end
end

class Conversation
  attr_reader :params

  def initialize(params)
    @params = params
  end

  def is_meaningless?
    params.nil? || params.empty?
  end

  def is_asking?
    params.end_with?("?")
  end

  def is_shouting?
    params == params.upcase
  end
end
