class Phrase
  def initialize(string)
    @string = string
  end

  def word_count
    hash = Hash.new
    table = create_table
    table.each_with_index { |item, index|
      hash[item] = table.count(item) }
    hash
  end

  def create_table
    @string.downcase.gsub(/[^a-z0-9'\s]/, ' ').split(" ")
  end
end
