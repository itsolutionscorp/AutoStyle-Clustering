class Bob
  def hey(msg)
    case
      when is_silent?(msg)   then 'Fine. Be that way!'
      when is_shouting?(msg) then 'Woah, chill out!'
      when is_asking?(msg)   then 'Sure.'
      else 'Whatever.'
    end
  end

  def is_silent?(msg)
    msg.strip == ''
  end

  def is_shouting?(msg)
    msg.tr("^A-Z",'') != '' && !msg.match(/[a-z]+/)
  end

  def is_asking?(msg)
    msg.strip[-1] == '?'
  end
end
