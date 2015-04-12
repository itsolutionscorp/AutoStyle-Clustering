class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    count_hash = {}
    @string.delete!(":!!&@$%^&.")
    @string.gsub!(',', ' ')
    @string.downcase.split(/  | /).each do |word|
      if count_hash.has_key?(word)
        count_hash[word] += 1
      else
        count_hash[word] = 1
      end
    end
    count_hash
  end

end
