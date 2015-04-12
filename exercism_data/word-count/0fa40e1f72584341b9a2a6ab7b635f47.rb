class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    Hash.new(0).tap do |word_count|
      split_string.each { |word| word_count[word.downcase] += 1 }
    end
  end

  private
  def split_string
    @string.split(/\W/).reject { |word| word.empty? }
  end
end
