class Bob

  REPARTEE = {   shout: "Woah, chill out!",   question: "Sure.",
               silence: "Fine. Be that way!", nonsense: "Whatever."}

  def shouted? message
    msg = message.split(/[^A-Za-z]/).join
    msg == msg.upcase && msg.split(/[^A-Z]/).join.length>0
  end

  def type  message
    return :silence  if message.gsub(/\s/,'') == ''
    return :shout    if shouted? message
    return :question if message[-1] == '?'
    :nonsense
  end

  def hey message
    REPARTEE[type message]
  end
end
