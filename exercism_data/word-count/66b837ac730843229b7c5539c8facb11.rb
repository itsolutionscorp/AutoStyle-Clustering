class Phrase < Struct.new(:string)
  def word_count
    words = string.downcase.scan(/\w+/)
    words.each_with_object(Hash.new(0)) do |current, words|
      words[current] += 1
    end
  end
end
