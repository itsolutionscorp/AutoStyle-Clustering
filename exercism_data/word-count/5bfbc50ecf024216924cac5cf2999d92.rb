class Phrase
  attr_reader :text
  private :text

  def initialize(text)
    @text = text
  end

  def word_count
    valid_fragments.each_with_object(Hash.new(0)) { |fragment, frequencies|
      frequencies[fragment.downcase] += 1
    }
  end

  private
  def valid_fragments
    text.scan(/\w+/)
  end
end
