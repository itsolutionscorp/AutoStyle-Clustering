class Phrase
  def initialize(string)
    @string = string.strip
    @words = []
  end
  
  def word_count
    string_array_as_count_hash
  end
  
  private
  
  def string_array_as_count_hash
    string_to_array.uniq.each do |word|
      unless word.nil? || word == ''
        @words << hash_entry_for(word)
      end
    end
    Hash[@words]
  end
  
  def hash_entry_for(word)
    key, value = word, string_to_array.count(word)
  end
  
  def string_to_array
    @string_to_array ||= split_string.map! { |word| clean_string(word) }
  end
  
  def clean_string(word)
    word.gsub(/\W*/,'').downcase
  end
  
  def split_string
    @split_string ||= @string.split(/\W/)
  end
end
