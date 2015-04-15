class Phrase

  def initialize(phrase)
    @counts = Hash.new(0)
    phrase.scan(/\w+/) do |word|
      @counts[word.downcase] += 1
    end
  end

  def word_count
    @counts
  end

end
