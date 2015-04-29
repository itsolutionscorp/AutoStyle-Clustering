class Bob
  def hey(input)
    sentence = Sentence.new(input)

    if sentence.is_shouting?
      "Woah, chill out!"
    elsif sentence.is_question?
      "Sure."
    elsif sentence.is_silence?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  class Sentence
    def initialize(content_in)
      @content=content_in
    end

    def is_question?
      @content.end_with?("?")
    end

    def is_silence?
      @content.strip.empty?
    end

    def is_shouting?
      @content == @content.upcase and @content != @content.downcase
    end
  end
end
