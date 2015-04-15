class Anagram
  def initialize(word)
    @word = sort_letters word
  end

  def match(list)
    list.select { |w| sort_letters(w) == @word }
  end

  private

  def sort_letters(string)
    string.downcase.scan(/\w/).sort.join
  end
end
