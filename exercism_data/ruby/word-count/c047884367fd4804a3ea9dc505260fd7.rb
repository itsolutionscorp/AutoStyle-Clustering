class Phrase
  def initialize(document)
    @document = document
    @word_list = nil
  end
  def word_count
    return @word_list if @word_list
    @word_list = {}
    words = @document.downcase.gsub(",", " ").gsub(/[^\w\d\s]*/, "").split(" ")
    words.each do |word|
      @word_list[word] ||= 0
      @word_list[word] += 1
    end
    @word_list
  end
end
