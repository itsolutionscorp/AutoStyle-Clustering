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
      @subject = subject
    end

    def matches?(text)
      sort_chars(@subject) == sort_chars(text)
    end

    private

    def sort_chars(text)
      text.downcase.chars.sort
    end
  end

end
