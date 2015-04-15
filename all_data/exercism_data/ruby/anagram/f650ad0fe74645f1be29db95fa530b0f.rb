class Anagram

  def initialize word
    @word = word.to_s
  end

  def match words
    words.select { |w| match?(w) }
  end

  def match? str
    this  = @word.downcase
    other = str.to_s.downcase
    if this == other
      false
    else
      sort(this) == sort(other)
    end
  end

  private

  def sort word
    word.chars.sort.join
  end

end
