class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    matches = []
    list.each do |word|
      next if word.length != length
      next if @word.downcase == word.downcase
      matches << word if word.downcase.split(//).sort == chars
    end

    matches
  end

  private

  def length
    @word.length
  end

  def chars
    @word.downcase.split(//).sort
  end

end
