class Anagram

  def initialize(source)
    @word = source.downcase.strip
  end

  def match(words)
    words.select {|w| anagram?(w.downcase)}
  end

private

  def anagram?(w)
    !@word.eql?(w) and w.chars.sort == source_chars
  end

  def source_chars
    @source_chars ||= @word.chars.sort
  end
end
