# Oops, there was a bug in the commenting system :)
#
# Ha! Perhaps the question should not be so easily solved with a regular
# expression and a textbook use of the case statement? :)
class Bob
  def hey(message)
    if empty?(message)
      'Fine. Be that way.'
    elsif question?(message)
      'Sure.'
    elsif shout?(message)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def empty?(message)
    message.nil? || message.empty?
  end

  def question?(message)
    message[-1] == '?'
  end

  def shout?(message)
    !statement?(message)
  end

  def statement?(message)
    message =~ /[a-z]/
  end
end
