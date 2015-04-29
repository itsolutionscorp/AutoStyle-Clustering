class Bob

  MOODS = { shout: 'Woah, chill out!', speak: 'Whatever.', ask: 'Sure.' }

  def hey(what)
    is_something?(what) ? say(what) : say_nothing
  end

  private

  def say(what)
    case
    when what.end_with?(".") then  speak(what)
    when what.end_with?("!") then  shout(what)
    when what.end_with?("?") then  ask(what)
    when what.empty?         then  say_nothing
    else is_upcase?(what) ? MOODS[:shout] : nil
    end
  end

  def speak(what)
    MOODS[:speak]
  end

  def ask(what)
    MOODS[:ask]
  end

  def shout(what)
    !is_upcase?(what) ? MOODS[:speak] : MOODS[:shout] if got_numbers?(what) || got_special_chars?(what)
  end

  def got_special_chars?(what); what =~ /\W/; end
  def got_numbers?(what); what =~ /\d/; end
  def got_question_mark?(what); what =~ /\?/; end
  def is_something?(meaningful); meaningful != nil; end
  def say_nothing; 'Fine. Be that way.'; end
  def is_upcase?(what); what == what.upcase; end

end
