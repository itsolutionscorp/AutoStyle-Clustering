class Bob
  def hey(statement)
    case how_do_you_speak_to_me?(statement)
    when :question
      "Sure."
    when nil
      "Fine. Be that way!"
    when :shout
      "Woah, chill out!"
    when :unknown
      "Whatever."
    else
      nil
    end
  end

  def how_do_you_speak_to_me?(statement)
    statement.chomp!
    case
    when statement.end_with?("?")
      :question
    when statement.empty?
      nil
    when statement.upcase?
      :shout
    else
      :unknown
    end
  end
end

# yay Monkeypatching!
class String
  def upcase?()
    # because StackOverflow says so
    # http://stackoverflow.com/a/12713365
    !/[[:lower:]]/.match(self)
  end
end
