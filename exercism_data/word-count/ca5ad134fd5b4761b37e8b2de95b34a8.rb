class Phrase

  attr_reader :string

  def initialize(string)
    @string = string
  end

  def word_count
    Hash.new(0).tap do |hash|
      words.each {|word| hash[word] += 1 }
    end
  end

  private

  def words
    string.scan(/(\w|\')?/).flatten.slice_before(&:nil?)
      .map(&:join).map(&:downcase)
      .select {|str| !str.empty? }
  end
end
