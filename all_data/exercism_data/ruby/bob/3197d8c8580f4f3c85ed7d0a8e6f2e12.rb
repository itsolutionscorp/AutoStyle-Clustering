class Bob
  attr_reader :heard
  
  def hey(heard)
    @heard = heard
    respond!
  end

  def responses
    {
      :chill    => "Woah, chill out!",
      :whatever => "Whatever.",
      :sure     => "Sure.",
      :fine     => "Fine. Be that way!"
    }
  end

  private

  def respond!
    thought = process_what_was_said
    responses.fetch(thought) { "Whatever." }
  end

  def process_what_was_said
    if mute?
      :fine
    elsif being_shouted_at?
      :chill
    elsif being_asked?
      :sure
    else
      :whatever
    end
  end

  def being_asked?
    heard.end_with?("?")
  end

  def mute?
    heard.nil? || heard == ""
  end

  def being_shouted_at?
    heard == heard.upcase
  end

end
