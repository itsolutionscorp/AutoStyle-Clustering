class Bob
  LIKE_I_CARE = 'Fine. Be that way!'
  CHILL       = 'Woah, chill out!'
  MEH         = 'Sure.'
  DEFAULT     = 'Whatever.'

  BRAIN = {
    cat_got_your_tongue?:   LIKE_I_CARE,
    dude_shouting_at_me?:   CHILL,
    dude_asking_shizzle?:   MEH
  }

  attr_reader :message

  def hey(message)
    @message = message
    respond
  end

  def cat_got_your_tongue?
    message.strip.length == 0
  end

  def dude_shouting_at_me?
    !!message.chars.select { |c| c.match(/[A-Za-z]/)  }.map { |c| !!c.match(/[A-Z]/) }.reduce(&:&)
  end

  def dude_asking_shizzle?
    message[-1] == '?'
  end

  def respond
    key = BRAIN.keys.find { |name| method(name).call }
    key && BRAIN[key] || DEFAULT
  end
end
