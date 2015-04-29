class Bob

  def hey some_words
    @some_words = some_words
    making_a_big_deal ?  'Woah, chill out!'
      : dumb_question ? 'Sure.'
      : cold_shoulder ? 'Fine. Be that way!'
      : 'Whatever.'
  end

  private

  def making_a_big_deal
    @some_words.upcase == @some_words && @some_words.match(/[A-Z]/)
  end

  def dumb_question
    @some_words.end_with? '?'
  end

  def cold_shoulder
    '' == @some_words.strip
  end
end
