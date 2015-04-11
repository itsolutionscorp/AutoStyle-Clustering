class Bob

  def shouted? message
    msg = message.split(/[^A-Za-z]/).join
    msg == msg.upcase && msg.split(/[^A-Z]/).join.length>0
  end

  def attributes message
    att = []
    att << :silence  if message.gsub(/\s/,'') == ''
    att << :question if message[-1] == '?'
    att << :shout    if shouted? message
    att << :numbers  if message.split(/\d+/) == []
    att
  end

  def hey message
    it = attributes message
    return "Woah, chill out!"   if it.include? :shout
    return "Sure."              if it.include? :question
    return "Fine. Be that way!" if it.include? :silence
    return "Whatever."
  end
end
