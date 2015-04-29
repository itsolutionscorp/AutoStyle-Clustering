#
# == Bob
# bob is a simple robot just for fun 
class Bob
  # Check if the string contains no LowerCase and at least one UpperCase character.
  #
  # ==== Parameters
  # * +message+ - the message string
  #
  # ==== Return
  # * +True+  - the message match the specific rule
  # * +False+ - the message can't match the rule
  def isLettersRegularCase(message)
    findUpperChar = false
    message.each_char { |c|
      findUpperChar = true if c>='A' && c<='Z'
      return false if c >='a' && c<='z'
    }
    return findUpperChar
  end
  # The hey method of bob to say with input message
  #
  # ==== Parameters
  # * +message+ - the message string
  #
  # ==== Return
  # the message by what you say to bob.
  def hey(message)
    if message.strip == ''
      return 'Fine. Be that way!'
    elsif isLettersRegularCase(message)
      return 'Woah, chill out!'
    elsif message[-1, 1] == '?'
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
