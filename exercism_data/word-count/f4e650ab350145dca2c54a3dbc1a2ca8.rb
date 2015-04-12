class Phrase
  def initialize string
    @string = string.downcase
  end

  def word_count
    @string.split(/\W/).each_with_object(Hash.new(0)) do |word, accumulator|
      accumulator[word] += 1 unless word.empty?
    end
  end
end
