class Words
  attr_reader :count

  def initialize(text_to_scan)
    @count = Hash.new(0)
    list_of_words(text_to_scan).each {|word| @count[word] += 1}
  end

  private

  def list_of_words(full_text)
    normalize(full_text).split
  end

  def normalize(text)
    text.gsub(/\W+/, ' ').downcase
  end
end
