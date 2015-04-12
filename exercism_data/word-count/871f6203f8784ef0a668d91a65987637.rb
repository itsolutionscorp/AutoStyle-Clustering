class Phrase < Struct.new(:string)
  def word_count
    words = string.downcase.scan(/\w+/)
    words.inject(Hash.new(0)) do |words, current|
      words[current] += 1
      words
    end
  end
end
