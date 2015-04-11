class Bob

  def hey(vocalization)
    produce_reaction_for(ResponseType.from(vocalization))
  end

  def produce_reaction_for(response)
    case response
    when :disjointed then "Whatever."
    when :silent then "Fine. Be that way!"
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
    when disjointed? then :disjointed
    when silent? then :silent
    when loud? then :loud
    when interrogative? then :interrogative
    else nil
    end
  end

  private

  def human_tongue?
    @response.match(/[a-zA-Z]{1,}\W?/)
  end

  def disjointed?
    @response.include? "\n"
  end

  def silent?
    (@response.nil? || @response.empty?) ||
      (@response.length != 0 && @response.match(/^\s*$/))
  end

  def loud?
    human_tongue? && @response == @response.upcase
  end

  def interrogative?
    @response.end_with?("?")
  end
end
