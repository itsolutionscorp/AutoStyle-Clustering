class Phrase
  attr_reader :word_count

  def initialize(phrase)
    @word_count = count split(phrase)
  end

  private

  def split(phrase)
    phrase
      .downcase
      .gsub(/,|\s+/, ' ')
      .gsub(/[^a-z0-9' ]/, '')
      .split(' ')
  end

  def count(words)
    hash = {}

    words.each do |word|
      begin
        hash[word] += 1
      rescue
        hash[word] = 1
      end
    end

    hash
  end
end
