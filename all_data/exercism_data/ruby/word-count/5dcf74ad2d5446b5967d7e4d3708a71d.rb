class Phrase

  def initialize(input = '')
    @phrase = input
  end

  def word_count
    instances = Hash.new { |word, count| word[count] = 0 }
    @phrase.downcase.scan(/\w+/).each do |word|
      instances[word] += 1
    end
    instances
  end
end
