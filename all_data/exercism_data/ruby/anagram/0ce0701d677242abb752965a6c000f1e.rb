class Anagram

  def initialize(target_word)
    @target_word = target_word;
  end

  def match(words)
    words.find_all do |candidate_word|
      unless same_as_target_word?(candidate_word) 
        same_length_and_letters?(candidate_word)
      end 
    end
  end

  private

  attr_reader :target_word

  def tokenize(word)
    word.downcase.split('').sort
  end

  def same_length_and_letters?(candidate_word)
    tokenize(target_word) == tokenize(candidate_word)
  end

  def same_as_target_word?(candidate_word)
    target_word.downcase == candidate_word.downcase
  end

end
