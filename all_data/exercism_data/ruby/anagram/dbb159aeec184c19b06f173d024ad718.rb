class Anagram
  def initialize(word)
    @word = word.strip.downcase
  end

  def match(candidates)
    @candidates = candidates
    reject_identical_words
    generate_array_of_hashes_that_map_word_to_its_sorted_word
    select_hashes_where_sorted_word_matches_with_input_word
    select_original_candidates_which_matched_to_input_word
  end

  private

  def reject_identical_words
    @candidates.reject! { |c| c == @word}
  end

  def generate_array_of_hashes_that_map_word_to_its_sorted_word
    @candidates.map! { |c| { c => sort_word(c) } }
  end

  def select_hashes_where_sorted_word_matches_with_input_word
    @candidates.select! { |c| c.values[0] == sort_word(@word) }
  end

  def select_original_candidates_which_matched_to_input_word
    @candidates.map! { |c| c.keys }.flatten
  end

  def sort_word(word)
    word.chars.sort.join
  end
end
