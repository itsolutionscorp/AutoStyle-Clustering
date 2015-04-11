# bob is a typical teenager that is barely even listening.
# he will respond to your statements or questions in
# typical teenager fashion.
class Bob
  def hey(message)
    if (message =~ /[A-Z]/) && !(message =~ /[a-z]/)
      'Whoa, chill out!'
    elsif message =~ /[?]\z/
      'Sure.'
    elsif message =~ /[a-zA-Z0-9]/
      'Whatever.'
    else
      'Fine. Be that way!'
    end
  end
end
