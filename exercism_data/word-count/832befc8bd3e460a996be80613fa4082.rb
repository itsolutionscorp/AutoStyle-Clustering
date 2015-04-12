class Phrase < Struct.new(:phrase)
  def word_count
    hash = Hash.new(0)
    phrase.downcase.scan(/\w+/).each_with_object(hash) do |word, result|
      result[word] += 1
    end
  end
end
