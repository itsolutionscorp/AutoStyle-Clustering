class Bob
  def hey(text)
    something = Something.new(text)
    return "Fine. Be that way!" if something.empty?
    return "Woah, chill out!" if something.loud?
    return "Sure." if something.question?
    "Whatever."
  end

  Something = Struct.new(:text) do
    def empty?
      text.nil? || text.strip == ''
    end

    def loud?
      text.upcase == text
    end

    def question?
      text[-1] == '?'
    end
  end
end
