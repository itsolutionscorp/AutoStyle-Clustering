class Phrase
  attr_reader :word_count

  def initialize(content)
    @word_count = content.scan(/\w+/).each_with_object(Hash.new(0)) { |e, a|
      a[e.downcase] += 1
    }
  end
end
