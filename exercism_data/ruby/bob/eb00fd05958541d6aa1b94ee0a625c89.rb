class ConversationPartner
  def initialize(statement)
    @statement = statement
  end

  def silent?
    @statement.nil? || @statement.strip.empty?
  end

  def angry?
    !silent? && @statement == @statement.upcase
  end

  def enquiring?
    @statement.end_with?("?")
  end
end

class Bob
  def hey(statement)
    respond ConversationPartner.new(statement)
  end

  def respond(conversation_partner)
    if conversation_partner.silent?
      "Fine. Be that way!"
    elsif conversation_partner.angry?
      "Woah, chill out!"
    elsif conversation_partner.enquiring?
      "Sure."
    else
      "Whatever."
    end
  end
end
