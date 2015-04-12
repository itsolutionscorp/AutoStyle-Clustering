class Phrase
  def initialize(words)
    @words = words.downcase.gsub(","," ").gsub(/[^0-9a-z "'"]/i, "").squeeze(" ")
  end

  def word_count
    count = {}
    @words.split(" ").each do |word|
      if count[word] == nil
        count[word] = 1
      else
        count[word] += 1
      end
    end
    count
  end
end
