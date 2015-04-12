class Phrase
  def initialize(string)
    @input = string
  end

  def word_count
    running_count   = {}
    groomed_words   = @input.downcase.gsub( /[^\w\s]/, ' ' ).strip.split

    groomed_words.uniq.each do |word|
      running_count[word] = groomed_words.count(word)
    end
    running_count
  end
end
