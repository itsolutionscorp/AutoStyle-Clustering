class Phrase

  def initialize(word)
    @word = word.gsub(/[^'\w]/, " ").downcase
  end

  def word_count
    word_hash = @word.split(' ').map { |x| [x, 0] }.to_h
      word_hash.each do | key, value |
        word_hash[key] = @word.split(' ').count(key)
      end
    word_hash
  end

end
