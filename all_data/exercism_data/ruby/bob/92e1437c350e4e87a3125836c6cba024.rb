class Bob
  def hey(text)
    something = Something.new(text)
    answer(something)
  end

  def answer(something)
    case
    when something.silent?
      "Fine. Be that way!"
    when something.loud?
      "Woah, chill out!"
    when something.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Something
    def initialize(text)
      @text = text
    end

    def silent?
      text.nil? || text.strip == ''
    end

    def loud?
      text.upcase == text
    end

    def question?
      text.end_with? '?'
    end

    private
    def text
      @text
    end
  end
end
