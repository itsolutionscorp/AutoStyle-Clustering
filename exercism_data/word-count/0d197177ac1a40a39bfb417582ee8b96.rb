class Words
  def initialize(text)
    @text = text
  end

  def count
    count_occurences(create_list(@text))
  end

  private

  def create_list(text)
    text.downcase.gsub(/[^\w ]/, '').split(" ")
  end

  def count_occurences(list)
    list.inject(Hash.new(0)) {|list, word|
      list[word] += 1
      list
    }
  end
end
