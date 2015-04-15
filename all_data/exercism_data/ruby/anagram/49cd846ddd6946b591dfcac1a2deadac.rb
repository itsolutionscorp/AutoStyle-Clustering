class Anagram
  def initialize(word)
    @search = sort_letters word
  end

  def match(list)
    list.select { |w| sort_letters(w) == @search }
  end

  private

  def sort_letters(string)
    string.downcase.chars.sort
  end
end
