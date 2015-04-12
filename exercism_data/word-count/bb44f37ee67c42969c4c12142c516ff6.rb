class Phrase
  def initialize (phrase)
    @phrase = phrase
  end

  def word_count
    word_list = extract_words
    words = {}

    word_list.each do |word|
      word = word.downcase
      unless words.has_key?(word)
        words[word] = 0
      end

      words[word] += 1
    end

    words
  end

  private

  def extract_words
    # remove punctuation and other caracters that shall not count
    @phrase.gsub(/[!!&@$%^&:.]/, '').
    # split by any whitespace character and ,
    split(/\s|,/).
    # remove any empty words (e.g. caused by a whitespace immediately followed by a ,)
    select() {|item| item.length > 0}

  end
end
