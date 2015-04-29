class Bob
  def hey(text)
    Statement.new(text).reply_with(replies)
  end

  def replies
    @replies ||= {
      silence:  'Fine. Be that way!',
      shouting: 'Woah, chill out!',
      question: 'Sure.',
      default:  'Whatever.'
    }
  end
end

class Statement
  def initialize(text)
    @text = text
  end

  def reply_with(replies)
    if is_silence?
      replies[:silence]
    elsif is_shouting?
      replies[:shouting]
    elsif is_question?
      replies[:question]
    else
      replies[:default]
    end
  end

  private

  def is_silence?
    @text.match(%r{\A\s*\z})
  end

  def is_shouting?
    @text.match(%r{\A[^a-z]*[A-Z]+[^a-z]*\z})
  end

  def is_question?
    @text.match(%r{\?\z})
  end
end
