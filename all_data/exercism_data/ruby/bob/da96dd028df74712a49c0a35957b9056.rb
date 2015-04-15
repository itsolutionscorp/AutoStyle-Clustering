class Bob

  def hey(input)
    case input
    when you_shout? then bob_replies 'Woah, chill out!'
    when you_ask? then bob_replies 'Sure.'
    when you_dont_say_anything? then bob_replies 'Fine. Be that way!'
    else 'Whatever.'
    end
  end

  private

  def you_shout?
    ->(str) { str == str.upcase && str =~ /[[:alpha:]]/ }
  end

  def you_ask?
    ->(str) { str.end_with?('?') }
  end

  def you_dont_say_anything?
    ->(str) { !(str =~ /\S/) }
  end

  def bob_replies(message)
    message
  end
end
