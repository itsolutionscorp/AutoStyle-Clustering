module TeenSpeak
  extend self

  def talk
    "Whatever."
  end

  def shout
    "Woah, chill out!"
  end

  def question
    "Sure."
  end

  def silence
    "Fine. Be that way!"
  end
end

module LangMatcher
  extend self
  def call(msg)
    shout?(msg)      ||
      question?(msg) ||
      silence?(msg)  ||
      talk?
  end

  private

  def shout?(msg)
    msg == msg.upcase && no_alphas?(msg) ? TeenSpeak.shout : nil
  end

  def no_alphas?(msg)
    msg =~ /[[:alpha:]]+/
  end

  def question?(msg)
    msg[-1] == '?' ? TeenSpeak.question : nil
  end

  def silence?(msg)
    msg.strip == "" ? TeenSpeak.silence : nil
  end

  def talk?
    TeenSpeak.talk
  end
end


class Bob

  def hey(msg)
    LangMatcher.call(msg)
  end

end
