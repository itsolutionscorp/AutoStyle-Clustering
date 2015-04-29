class Anagram
  attr_reader :options

  def initialize(word)
    @options = AnagramSubject.new(word)
  end

  def match(candidates)
    candidates.select{ |candidate| options.anagram_of?(candidate)}
  end
end


class AnagramSubject
  attr_reader :options

  def initialize(options)
    @options = options
  end

  def anagram_of?(word)
    !duplicate(word) && format == edit(word)
  end

  def duplicate(word)
    word.downcase == options.downcase
  end

  def edit(word)
    word.downcase.chars.sort
  end

  def format
    @format ||= edit(options)
  end
end
