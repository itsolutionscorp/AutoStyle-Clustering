class Words
  def initialize(input)
    @input = input
  end

  def count
    word_list.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  def word_list
    @input.downcase.scan %r{\w+}
  end
end
