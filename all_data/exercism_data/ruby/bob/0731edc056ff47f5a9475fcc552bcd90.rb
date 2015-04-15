class Bob
  def hey(phrase)
    if is_empty?(phrase)
      'Fine. Be that way!'
    elsif is_yelling?(phrase)
      'Woah, chill out!'
    elsif is_question?(phrase)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

    def is_empty?(phrase)
      phrase.strip == ''
    end

    def is_yelling?(phrase)
      phrase.upcase == phrase &&       # is upcase and
      phrase.downcase != phrase.upcase # case matters
    end

    def is_question?(phrase)
      phrase[-1] == '?'
    end
end
