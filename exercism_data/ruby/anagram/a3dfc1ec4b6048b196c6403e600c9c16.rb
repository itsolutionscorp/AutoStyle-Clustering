class Anagram
  attr_reader :target_word

  def initialize(word)
    @target_word = String word
    @lookup_key = downcase_and_sort_char @target_word
  end

  def match(dictionary)
    matcher = {}
    dict = delete_target_word_from(Array dictionary)

    dict.each do |w|
      matcher[w] = downcase_and_sort_char w
    end

    find_anagram_in matcher
  end

  private
  def downcase_and_sort_char(word)
    word.downcase.split(//).sort.join
  end

  def delete_target_word_from(ary)
    ary.delete_if do |w|
      w.downcase == target_word.downcase
    end
  end

  def find_anagram_in(matcher)
    matcher.keys.select do |w|
      matcher[w] == @lookup_key
    end
  end

end
