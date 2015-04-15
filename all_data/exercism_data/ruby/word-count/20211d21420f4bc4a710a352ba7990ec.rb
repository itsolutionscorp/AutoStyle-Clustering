class Phrase
  def initialize(phrase)
    @phrase = remove_punctuation(phrase).downcase
  end

  def word_count
    counts = Hash.new {|hash, key| hash[key] = 0 }

    words.each do |word|
      counts[word] += 1 unless word.empty?
    end

    counts
  end

  def words
    @phrase.split(/[ ,]/)
  end

  def remove_punctuation(word)
    word.gsub(/[\&\%\^\.\!\@\$\:]/,"")
  end
end
