class Bob

  def hey(what_they_said)
    @last_heard = what_they_said || ''

    mouth_off
  end

  def mouth_off
    case what_was_heard
    when wound_up?  then "Woah, chill out!"
    when prompting? then "Sure."
    when silent?    then "Fine. Be that way."
    else                 "Whatever."
    end
  end

  private

  def what_was_heard
    comment + punctuation
  end

  def comment
    @last_heard.gsub(/\W/,'')
  end

  def punctuation
    @last_heard.reverse.chr
  end

  def wound_up?
    /[A-Z]{2}+/
  end

  def prompting?
    /.+\?$/
  end

  def silent?
    /^$/
  end

end
