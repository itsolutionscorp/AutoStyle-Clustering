class Phrase

  def initialize value
    @value = value.to_s.downcase
  end

  def word_count
    words = @value.split(/\W+/)
    count = Hash.new(0)
    words.each { |word| count[word] += 1 }
    count
  end

end
