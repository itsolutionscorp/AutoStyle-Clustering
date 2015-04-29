class Bob
  def hey message
    message = message.to_s.strip
    if message == message.upcase && message != ''
      'Woah, chill out!'
    elsif message.chars.last == '?'
      'Sure.'
    elsif message == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

#Bob is a lackadaisical teenager. In conversation, his responses are very limited.
#
#Bob answers 'Sure.' if you ask him a question.
#
#He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
#
#He says 'Fine. Be that way!' if you address him without actually saying anything.
#
#He answers 'Whatever.' to anything else.
