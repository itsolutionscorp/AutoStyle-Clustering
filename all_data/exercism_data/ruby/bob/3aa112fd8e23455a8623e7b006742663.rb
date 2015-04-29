class Bob
  attr_reader :heard

  def hey(heard)
    @heard = heard
    respond
  end

  def responses
    {
      :chill    => "Woah, chill out!",
      :whatever => "Whatever.",
      :sure     => "Sure.",
      :fine     => "Fine. Be that way!"
    }
  end

  def respond
    response = process_what_was_said
    responses.fetch(response) { "Whatever." }
  end

  def process_what_was_said
    if being_shouted_at?
      :chill
    elsif asked_politely?
      :sure
    elsif were_forceful? || used_acronyms?
      :whatever
    elsif mute?
      :fine
    else
      :whatever
    end
  end

  def being_shouted_at?
    heard =~ /^[^a-z]+\!?$/
  end

  def asked_politely?
    heard =~ /^[A-Z]?[a-z\s]+\?$/
  end

  def were_forceful?
    heard =~ /[A-Z]*.*\!/
  end
  
  def used_acronyms?
    heard =~ /[A-Z]{2,}/
  end

  def mute?
    heard.nil? || heard == ""
  end

end
