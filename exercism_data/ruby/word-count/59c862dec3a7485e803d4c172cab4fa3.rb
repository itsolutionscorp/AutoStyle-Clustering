class Phrase

	attr_accessor :str

  def initialize(str)
    @str = str
  end

  def word_count
    word_list = Hash.new(0)
    words { |word| word_list[word] += 1 }
    word_list
  end

	private

  def words
    str.downcase.scan(/[\w']+/) { |word| yield word }
  end

end
