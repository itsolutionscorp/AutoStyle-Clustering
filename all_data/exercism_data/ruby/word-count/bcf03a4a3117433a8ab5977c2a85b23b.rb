class Phrase
  def initialize(string)
    @phrase_arr = string.downcase.scan(/[\w']+/)
    @counts = {}
  end

  def word_count
    create_hash
    increment_values
    @counts
  end

  def create_hash
    @phrase_arr.each do |word|
      @counts[word] = 0
    end
  end

  def increment_values
    @phrase_arr.each do |word|
      @counts[word] += 1
    end
  end
end
