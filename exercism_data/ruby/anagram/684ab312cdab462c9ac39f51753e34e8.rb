class Anagram
  attr_reader :sorted_chars

  def initialize(word)
    @sorted_chars = sort_characters(word)
  end

  def match(options)
    options.each_with_object([]) do |option, a|
      a << option if anagram?(option)
    end
  end

  def anagram?(option)
    sorted_chars == sort_characters(option)
  end

  def sort_characters(word)
    word.chars.to_a.sort.join("")
  end

end
