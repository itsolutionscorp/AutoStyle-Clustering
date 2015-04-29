class Anagram
  def initialize(word)
    @word = word
    @word_hash = letter_hash(word)
  end

  def match(array_of_options)
    return [] unless array_of_options.respond_to? :each

    [].tap do |results|
      array_of_options.each do |option|
        if option.downcase != @word.downcase && letter_hash(option) == @word_hash
          results << option
        end
      end
    end
  end

  private
  def letter_hash(word)
    Hash.new(0).tap do |hash_for_word|
      word.downcase.split(//).each do |letter|
        hash_for_word[letter] += 1
      end
    end
  end
end
