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

  ENDS_WITH_EXCLAMATION   = %r{\!?$}
  ALL_CAPITALS            = %r{[A-Z0-9,]+}
  A_QUESTION              = %r{\?$}
  SENTENCE                = %r{^[A-Z]?[a-z\s]+}
  ACRONYMS                = %r{[A-Z]{2,}}

  def being_shouted_at?
    heard =~ /#{ALL_CAPITALS}(#{ENDS_WITH_EXCLAMATION}|#{A_QUESTION})/
  end

  def asked_politely?
    heard =~ /#{SENTENCE}#{A_QUESTION}/
  end

  def were_forceful?
    heard =~ ALL_CAPITALS
  end
  
  def used_acronyms?
    heard =~ ACRONYMS
  end

  def mute?
    heard.nil? || heard == ""
  end

end
