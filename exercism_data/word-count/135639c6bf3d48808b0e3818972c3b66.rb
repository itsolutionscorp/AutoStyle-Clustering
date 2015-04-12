class Words
  attr_reader :sentence

  def initialize(input)
    input.gsub!(/\W/, ' ')
    @sentence = input.downcase
  end

  def count
    results = Hash.new(0)
    sentence.split(' ').each do |word|
      results[word] += 1
    end
    results
  end
end
