class Anagram

  def initialize(subject)
    @matcher = Matcher.new(subject)
  end

  def match(word_list)
    word_list.select do |entry|
      @matcher.matches?(entry)
    end
  end

  class Matcher
    def initialize(subject)
      @normalized_subject = normalize(subject)
    end

    def matches?(text)
      @normalized_subject == normalize(text)
    end

    private

    def normalize(text)
      text.downcase.chars.sort
    end
  end

end
