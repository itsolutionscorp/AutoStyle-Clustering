class Phrase < Struct.new(:phrase)
  def word_count
    Hash.new(0).tap do |histogram|
      phrase.downcase.split(/(?!')[[:punct:]\W]+/).each do |word|
        histogram[word] += 1
      end
    end
  end
end
