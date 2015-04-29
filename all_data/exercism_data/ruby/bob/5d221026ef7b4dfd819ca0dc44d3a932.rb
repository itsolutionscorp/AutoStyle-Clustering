# I am lackadaisical teenager. In conversation, my responses are very limited.
class Bob
  attr_reader :heard

  def heard=(str)
    @heard = str.gsub(/\n/, ' ')
  end

  def hey(saying)
    self.heard = saying

    if yelled_at?
      'Woah, chill out!'
    elsif asked_question?
      'Sure.'
    elsif nothing_said?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def asked_question?
    # a question always ends with a ?
    heard.match(/\?$/)
  end

  def nothing_said?
    # nothing means nothing or only whitespace
    heard.match(/^\s*$/)
  end

  def yelled_at?
    # yells contain letters and are all uppercase
    contains_letters? && heard.eql?(heard.upcase)
  end

  def contains_letters?
    # bob heard some letters in there
    heard.match(/[a-zA-z]/)
  end
end
