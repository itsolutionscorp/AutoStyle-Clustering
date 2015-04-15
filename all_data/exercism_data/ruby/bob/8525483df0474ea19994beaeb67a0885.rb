class Bob
  def hey(content)
    Phrase.new(content).response
  end
end

class Phrase
  attr_reader :content

  def initialize(content)
    @content = content.rstrip
  end

  def response
    if is_silence?
      "Fine. Be that way!"
    elsif is_question?
      "Sure."
    elsif is_shout?
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  private

    def is_shout?
      content.match(/[A-Z]/) && content == content.upcase
    end

    def is_silence?
      content.empty?
    end

    def is_question?
      content.end_with?("?") && !is_shout?
    end

    def is_text?
      content.match(/[:alpha:]/)
    end
end
