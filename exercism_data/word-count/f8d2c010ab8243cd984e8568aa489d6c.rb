class Phrase < Struct.new(:string)
  def word_count
    words = string.scan(/\w+/)
    words.inject(Hash.new(0)) do |words, current|
      current = current.downcase
      words[current] += 1
      words
    end
  end
end
