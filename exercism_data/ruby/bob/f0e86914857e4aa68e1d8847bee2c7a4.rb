# Bob
# Bob is a lackadaisical teenager. In conversation, his responses are very limited.
# Bob answers 'Sure.' if you ask him a question.
# He answers 'Woah, chill out!' if you yell at him.
# He says 'Fine. Be that way!' if you address him without actually saying anything.
# He answers 'Whatever.' to anything else.

class Bob

  def hey(string)

    if string.gsub(/\s+/, '').length == 0 # string is null string or only contains white space
      return 'Fine. Be that way!'

    elsif string == string.upcase and string.match(/[A-Z]/) # string is all UPPERCASE (works only with ASCII strings!) AND has at least one letter
      return 'Woah, chill out!'

    elsif string.match(/\?$/) and not string.match(/\?./m) # string has question mark at end of line AND does NOT have a second line (i.e. any character following '?')
      return 'Sure.'

    else
      return 'Whatever.' # default response

    end

  end

end
