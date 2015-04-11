class Bob
  def hey(sentence)

    def silence?(sentence)
      sentence.nil? || sentence.empty?
    end

    def question?(sentence)
      sentence.chars.last == '?' && sentence != sentence.upcase
    end

    def yell?(sentence)
      sentence == sentence.upcase
    end

    case
      when silence?(sentence)
        "Fine. Be that way!"
      when question?(sentence)
        "Sure."
      when yell?(sentence)
        "Woah, chill out!"
      else
        "Whatever."
    end

  end
end
