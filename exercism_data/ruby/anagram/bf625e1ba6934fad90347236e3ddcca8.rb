class Anagram

  def initialize word
    @word = word
    @anagrams = @word.split('')
                     .permutation
                     .map &:join
  end

  def match test_words
    test_words.delete_if { |word| word.length != @word.length || @word =~ /#{word}/i }
    test_words.select do |word|
      counter = 0
      loop do
        break true if @anagrams[counter] =~ /#{word}/i
        counter += 1
        break false if counter >= @anagrams.size
      end
    end
  end

end
