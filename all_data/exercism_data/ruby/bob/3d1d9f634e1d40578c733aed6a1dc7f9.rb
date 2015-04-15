class Bob
  def hey msg=''
    return "Fine. Be that way!" if msg.eql? ''
    return 'Fine. Be that way!' if msg.eql? '    '
    return "Woah, chill out!" if msg.upcase.eql? msg
    return "Sure." if msg[-1] == "?"
    "Whatever."
  end
end
