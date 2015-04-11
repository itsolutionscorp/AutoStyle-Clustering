class Bob
  def hey(msg)
    msg.strip!

    return 'Fine. Be that way!' if msg.empty?
    return 'Woah, chill out!' if msg.upcase == msg && /[A-Z]/ =~ msg
    return 'Sure.' if '?' == msg[-1]

    'Whatever.'
  end
end
