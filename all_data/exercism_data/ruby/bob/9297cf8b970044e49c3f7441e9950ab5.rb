require 'delegate'

class Message < SimpleDelegator
  KINDS = [ :silence, :shouting, :question ].freeze

  def initialize(text)
    super(text.to_s)
  end

  def kind
    what_kind = method(:is_kind?)
    KINDS.detect(&what_kind) || :unknown
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

  def is_kind?(name)
    return false unless KINDS.include? name.to_sym
    public_send("#{name}?")
  end

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
