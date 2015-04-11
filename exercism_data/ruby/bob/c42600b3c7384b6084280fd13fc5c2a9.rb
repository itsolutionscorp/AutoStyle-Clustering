class Bob

  class Answer < Struct.new(:answer, :matcher)
    def match? phrase
      matcher.call(phrase)
    end
  end

  def hey phrase
    answers.find{ |answer| answer.match? phrase.to_s }.answer
  end

  private

  def answers
    @answers ||= [
      Answer.new('Fine. Be that way!', -> phrase { phrase =~ /\A\s*\z/ }),
      Answer.new('Woah, chill out!', -> phrase { phrase.upcase == phrase }),
      Answer.new('Sure.', -> phrase { phrase[-1] == '?' }),
      Answer.new('Whatever.', -> phrase { true })
    ]
  end

end
