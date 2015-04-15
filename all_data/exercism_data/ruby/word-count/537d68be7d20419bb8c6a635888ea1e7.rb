class Phrase

  def initialize(args)
    @phrase = parse(args)
    @counts = Hash.new(0)
    @phrase.each { |word| @counts[word] += 1 }
  end

  def word_count
    @counts
  end

  private

  def parse(phrase)
    phrase.downcase!
    remove_punctuation(phrase)
  end
  
  def remove_punctuation(phrase)
    punctuation = /[!@$%^&:,.]/
    phrase.gsub(punctuation, ' ').split
  end

end
