class DefaultMessage
  def matches?(_); true; end
  def respond; 'Whatever.'; end
end

class ShoutingMessage
  def matches?(message); message !~ /[a-z]/; end
  def respond; 'Woah, chill out!'; end
end

class QuestionMessage
  def matches?(message); message.end_with?("?"); end
  def respond; 'Sure.'; end
end

class SilentMessage
  def matches?(message); message.nil? || message.empty?; end
  def respond; 'Fine. Be that way!'; end
end

class Bob
  MESSAGES = [SilentMessage, ShoutingMessage, QuestionMessage, DefaultMessage]

  def hey(message)
    MESSAGES.map(&:new).find do |command|
      command.matches?(message)
    end.respond
  end

end
