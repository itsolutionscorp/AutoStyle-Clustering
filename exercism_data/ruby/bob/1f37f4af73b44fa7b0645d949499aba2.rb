class Bob

  def hey (remark)
    # 'Sure.' if you ask him a question.
    # 'Whoa, chill out!' if you yell at him.
    # 'Fine. Be that way!' if you address him without actually saying anything.
    # 'Whatever.' to anything else.

    answer = 'Whatever.'
    answer = 'Sure.' if question?(remark)
    answer = 'Whoa, chill out!' if shout?(remark)
    answer = 'Fine. Be that way!' if silence?(remark)

    answer
  end

  private

  def shout? (remark)
    # when remark contains words (not only numbers for example)
    # and it is completely uppercased, than it's considered shouting
    remark.match(/[a-zA-Z]/) && remark.upcase() === remark
  end

  def question? (remark)
    remark.end_with?("?")
  end

  def silence? (remark)
    remark.strip.length === 0
  end
end
