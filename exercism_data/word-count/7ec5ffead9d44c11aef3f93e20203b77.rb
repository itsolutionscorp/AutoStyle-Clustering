class Phrase

  def initialize(content)
    @content = content
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

private

  def word_list
    @content.downcase.split(/\W+/)
  end

end
