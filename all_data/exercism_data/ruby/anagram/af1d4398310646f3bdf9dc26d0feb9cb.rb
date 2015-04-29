class Anagram

  def initialize(subject)
    @subject_histogram = histogram_of(subject)
  end

  def match(words)
    words.select { |w| anagram?(w) }
  end

  private

  def anagram?(word)
    @subject_histogram == histogram_of(word)
  end

  def histogram_of(word)
    histogram = Hash.new(0)
    word.downcase.chars.each do |c|
      histogram[c] += 1
    end
    histogram
  end

end
