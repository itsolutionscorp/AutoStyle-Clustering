class Anagram

  def initialize(word)
    @word = sorted(word)
  end

  def match(content)
    content.select {|elt| sorted(elt) == @word}
  end

private

  def sorted(text)
    text.downcase.chars.sort.join
  end

end
