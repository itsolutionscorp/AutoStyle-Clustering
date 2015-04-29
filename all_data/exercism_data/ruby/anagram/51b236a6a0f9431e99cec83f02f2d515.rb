class Anagram
  class Subject
    attr :payload

    def initialize(word)
      @payload = unpack(word)
    end

    def match?(other)
      payload.eql?(unpack(other))
    end

    private

    def unpack(word)
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
