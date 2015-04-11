class Bob
  def hey(english_sentence)
    what_i_heard = TeenagerSentence.new(english_sentence)

    case
    when what_i_heard.nothing?
      "Fine. Be that way!"
    when what_i_heard.yelling?
      "Woah, chill out!"
    when what_i_heard.question?
      "Sure."
    else
      "Whatever."
    end
  end

  TeenagerSentence = Struct.new(:english) do
    def nothing?
      english.strip.empty?
    end

    def question?
      english.end_with? '?'
    end

    def yelling?
      english.upcase == english
    end
  end
end
