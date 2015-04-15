class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = split_phrase

    results = count_words(words)

    print_results(results)
  end

  private
    def split_phrase
      @phrase.gsub(/[^-a-zA-Z0-9]/, ' ').downcase.split
    end

    def count_words(words)
      results = Hash.new(0)
      words.each do |word|
        results[word] += 1
      end
      results
    end

    def print_results(results)
      results.each do |word, count|
        puts "#{word}: #{count}"
      end
    end
end
