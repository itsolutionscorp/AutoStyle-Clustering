class Phrase
  attr_accessor :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = {}
    test = phrase.downcase.gsub(/,/, " ").gsub(/[^a-z0-9'\s]/i, '').split(" ")
    test.each do |sub|
      count[sub] = test.count { |str| str == sub }
    end
    count
  end
end
