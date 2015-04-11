class Bob
  def hey text

    if is_silence? text
      "Fine. Be that way!"
    elsif is_shouted? text
      "Woah, chill out!"
    elsif is_question? text
      "Sure."
    else
    "Whatever."
    end
  end

  private

    def is_question? text
      text.end_with? '?'
    end


    def is_shouted? text
      text == text.upcase
    end

    def is_silence? text
      text.strip == ''
    end

end
