class Anagram
  class Subject
    attr :payload

    def initialize(word)
      @payload = cipher(word)
    end

    def match?(other)
      payload.eql?(cipher(other))
    end

    private

    def cipher(word)
      word.downcase.chars.sort
    end
  end

  attr :subject

  def initialize(word)
    @subject = Subject.new(word)
  end

  def match(words)
    words.find_all { |word| subject.match?(word) }
  end
end
