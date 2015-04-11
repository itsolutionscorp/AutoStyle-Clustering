class Anagram
  def initialize(word)
    @word = word.downcase
    create_reference_word
  end

  def match(candidate_list)
    candidate_list.find_all do |candidate| 
      canonical_form(candidate) == @reference_word && candidate.downcase != @word
    end
  end

  private

  def canonical_form(word)
    word.downcase.chars.each_with_object(Hash.new(0)) {|obj, memo| memo[obj] += 1}
  end

  def create_reference_word
    @reference_word = canonical_form(@word)
  end
end
