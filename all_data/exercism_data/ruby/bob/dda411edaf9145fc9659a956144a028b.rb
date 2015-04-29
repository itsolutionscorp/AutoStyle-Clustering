class Bob
  def hey(msg)
    @msg = msg
    answer
  end

  private

  attr_reader :msg

  def answer
    case
    when shout? then 'Woah, chill out!'
    when question? then 'Sure.'
    when silent? then 'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shout?
    all_caps && some_keys
  end

  def all_caps
    msg == msg.upcase
  end

  def some_keys
    !msg.strip.empty?
  end

  def question?
    msg[-1, 1] == "?"
  end

  def silent?
   msg.strip.empty?
  end
end
