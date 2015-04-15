require 'delegate'

class Message < SimpleDelegator
  def initialize(text)
    super(text.to_s)
  end

  def kind
    what_kind = -> kind { send("#{kind}?") }
    [ :silence, :shouting, :question ].detect(&what_kind) || :unknown
  end

  def silence?
    empty?
  end

  def shouting?
    all_caps? and not empty?
  end

  def question?
    end_with? '?'
  end

  def all_caps?
    upcase == self
  end

  private

  def empty?
    strip.empty?
  end
end

class Bob
  def hey(message)
    message = Message.new(message)

    case message.kind
    when :silence
      'Fine. Be that way!'
    when :shouting
      'Woah, chill out!'
    when :question
      'Sure.'
    else
      'Whatever.'
    end
  end
end
