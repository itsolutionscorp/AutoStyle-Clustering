module ParsablePhrases
  def parsed
    @phrase.downcase.gsub(/\W+/, ' ').split(" ")
  end
end

class Phrase 
  include ParsablePhrases

  def initialize input
    @phrase = input
  end

  def word_count
    parsed.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end
end
