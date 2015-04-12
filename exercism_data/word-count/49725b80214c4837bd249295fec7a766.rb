class Phrase < String

  def word_count
    sanitized_word_list.each_with_object(Hash.new(0)) do |word, counter| 
      counter[word] += 1
    end
  end

  private

  def sanitized_word_list
    downcase.scan(/[\w]+/)
  end

end
