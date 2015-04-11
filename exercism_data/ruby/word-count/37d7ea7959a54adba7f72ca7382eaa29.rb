class Words
  attr_reader :sentence

  def initialize(string)
    @sentence = normalize_string(string)
  end

  def count
    results = Hash.new(0)
    sentence.split(' ').each do |word|
      results[word] += 1
    end
    results
  end

  private

  def normalize_string(string)
    string.gsub(/\W/, ' ').downcase
  end
end
