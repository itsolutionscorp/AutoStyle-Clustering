class Anagram
  attr_reader :test_word

  def initialize(test_word)
    @test_word = test_word.downcase
  end

  def word_stats(word)
    word.downcase.split("").inject(Hash.new(0)) { |h,v| h[v] += 1; h }
  end

  def match(word_list)
    word_list.delete_if { |word| word.downcase == test_word }
      word_list.find_all do |word|
        p word.class
        word_stats(test_word) == word_stats(word)
        # test_word.send(:word_stats) == word_stats(word) <= how can I refactor using send and refactor 'word_stats' to not require an argument?
      end
  end

end
