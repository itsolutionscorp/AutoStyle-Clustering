class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(word_list)
    word_list.reject { |match| match == word      }
             .select { |match| is_anagram?(match) }
  end

  private

  def is_anagram?(match)
   ((word.split(//) - match.split(//)) + (match.split(//) - word.split(//))).empty? &&
    match.downcase.split(//).sort == word.split(//).sort
  end

end
