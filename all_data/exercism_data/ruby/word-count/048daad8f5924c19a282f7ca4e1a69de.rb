class Phrase

  attr_reader :contents

  def initialize(message)
    @contents = message
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  def words
    contents.downcase.scan(/\w+/)
  end

end
