class Bob

  def hey(vocalization)
    produce_reaction_for(ResponseType.from(vocalization))
  end

  def produce_reaction_for(response)
    case response
    when :silent then "Fine. Be that way."
    when :loud then "Woah, chill out!"
    when :interrogative then "Sure."
    else "Whatever."
    end
  end

end

class ResponseType

  def self.from(vocalization)
    new(vocalization).type
  end

  def initialize(response)
    @response = response
  end

  def type
    case
    when silent? then :silent
    when loud? then :loud
    when interrogative? then :interrogative
    else nil
    end
  end

  private

  def silent?
    @response.nil? || @response.empty?
  end

  def loud?
    @response == @response.upcase
  end

  def interrogative?
    @response.end_with?("?")
  end
end
