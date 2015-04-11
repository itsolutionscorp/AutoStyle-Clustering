class Bob

  def hey(msg)
    respond_to msg
  end


  private

  def respond_to(msg)
    find_responder(msg).new.respond
  end

  def find_responder(msg)
    responders.find { |r| r.can_respond_to?(msg) }
  end

  def responders
    self.class.constants
      .select { |c| c.to_s.end_with?('Responder') }
      .map { |c| Kernel.const_get("Bob::#{c}") }
  end
end

class Bob::SilenceResponder
  def self.can_respond_to?(msg)
    msg.strip.empty?
  end

  def respond
    'Fine. Be that way!'
  end
end

class Bob::ShoutingResponder
  def self.can_respond_to?(msg)
    msg == msg.upcase
  end

  def respond
    'Woah, chill out!'
  end
end

class Bob::QuestionResponder
  def self.can_respond_to?(msg)
    msg.end_with?('?')
  end

  def respond
    'Sure.'
  end
end

class Bob::DefaultResponder
  def self.can_respond_to?(msg)
    true
  end

  def respond
    'Whatever.'
  end
end
