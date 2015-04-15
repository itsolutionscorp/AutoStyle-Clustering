class Bob
  def hey(msg)
    msg = msg.to_s

    return 'Fine. Be that way!' if msg.empty?
    return 'Woah, chill out!'   if msg =~ /\A[^a-z]+\z/
    return 'Sure.'              if msg.end_with? '?'

    'Whatever.'
  end
end
