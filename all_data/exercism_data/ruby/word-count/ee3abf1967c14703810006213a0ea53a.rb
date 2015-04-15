class Phrase

  def initialize(string)
    @string = string
  end

  def count_hash
    @count_hash ||= {}
  end

  def word_count
    unless @word_array
      word_array.each do |word|
        if count_hash.keys.include? word 
          count_hash[word] += 1
        else
          count_hash[word] = 1
        end
      end
    end
    count_hash
  end

  private

  def word_array
    @word_array ||= 
      @string.downcase.gsub(/[^a-z0-9\s]/i, '').split(/\s+/)
  end

end
