class Phrase
  attr_reader :text
  
  def initialize(text)
    @text = text
  end

  def word_count
    count_instances(words)
  end

  private

  def count_instances(objects)
    objects.each_with_object(Hash.new(0)) do |object, counts|
      counts[object] += 1
    end
  end

  def words
    text.downcase.scan(/\w+/)
  end
end
